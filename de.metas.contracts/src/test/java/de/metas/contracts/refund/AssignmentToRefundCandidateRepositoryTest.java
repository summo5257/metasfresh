package de.metas.contracts.refund;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.adempiere.model.InterfaceWrapperHelper.delete;
import static org.adempiere.model.InterfaceWrapperHelper.getTableId;
import static org.adempiere.model.InterfaceWrapperHelper.newInstance;
import static org.adempiere.model.InterfaceWrapperHelper.save;
import static org.adempiere.model.InterfaceWrapperHelper.saveRecord;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.adempiere.test.AdempiereTestHelper;
import org.adempiere.util.time.SystemTime;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_InvoiceSchedule;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.compiere.model.X_C_InvoiceSchedule;
import org.compiere.util.TimeUtil;
import org.junit.Before;
import org.junit.Test;

import de.metas.adempiere.model.I_C_Currency;
import de.metas.contracts.model.I_C_Flatrate_Conditions;
import de.metas.contracts.model.I_C_Flatrate_RefundConfig;
import de.metas.contracts.model.I_C_Flatrate_Term;
import de.metas.contracts.model.I_C_Invoice_Candidate_Assignment;
import de.metas.contracts.model.X_C_Flatrate_Conditions;
import de.metas.contracts.model.X_C_Flatrate_RefundConfig;
import de.metas.contracts.model.X_C_Flatrate_Term;
import de.metas.invoice.InvoiceScheduleRepository;
import de.metas.invoicecandidate.model.I_C_Invoice_Candidate;

/*
 * #%L
 * de.metas.contracts
 * %%
 * Copyright (C) 2018 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

public class AssignmentToRefundCandidateRepositoryTest
{
	private static final BigDecimal THREE = new BigDecimal("3");
	private static final BigDecimal NINE = new BigDecimal("9");

	private I_C_Invoice_Candidate assignableIcRecord;
	private I_C_Invoice_Candidate_Assignment assignmentRecord;
	private AssignmentToRefundCandidateRepository assignmentToRefundCandidateRepository;

	@Before
	public void init()
	{
		AdempiereTestHelper.get().init();

		final Timestamp dateToInvoiceOfAssignableCand = SystemTime.asTimestamp();

		final I_C_Currency currencyRecord = newInstance(I_C_Currency.class);
		currencyRecord.setStdPrecision(2);
		save(currencyRecord);

		final I_C_BPartner bPartnerRecord = newInstance(I_C_BPartner.class);
		save(bPartnerRecord);

		final I_C_UOM uomRecord = newInstance(I_C_UOM.class);
		saveRecord(uomRecord);

		final I_M_Product productRecord = newInstance(I_M_Product.class);
		productRecord.setC_UOM(uomRecord);
		save(productRecord);

		assignableIcRecord = newInstance(I_C_Invoice_Candidate.class);
		assignableIcRecord.setBill_BPartner(bPartnerRecord);
		assignableIcRecord.setM_Product(productRecord);
		assignableIcRecord.setDateToInvoice(dateToInvoiceOfAssignableCand);
		assignableIcRecord.setNetAmtInvoiced(ONE);
		assignableIcRecord.setNetAmtToInvoice(NINE);
		assignableIcRecord.setC_Currency(currencyRecord);
		save(assignableIcRecord);

		final I_C_Flatrate_Conditions conditionsRecord = newInstance(I_C_Flatrate_Conditions.class);
		conditionsRecord.setType_Conditions(X_C_Flatrate_Conditions.TYPE_CONDITIONS_Refund);
		saveRecord(conditionsRecord);

		final I_C_InvoiceSchedule invoiceSchedule = newInstance(I_C_InvoiceSchedule.class);
		invoiceSchedule.setInvoiceFrequency(X_C_InvoiceSchedule.INVOICEFREQUENCY_Daily);
		saveRecord(invoiceSchedule);

		final I_C_Flatrate_RefundConfig refundConfigRecord = newInstance(I_C_Flatrate_RefundConfig.class);
		refundConfigRecord.setC_Flatrate_Conditions(conditionsRecord);
		refundConfigRecord.setM_Product(productRecord);
		refundConfigRecord.setRefundInvoiceType(X_C_Flatrate_RefundConfig.REFUNDINVOICETYPE_Creditmemo);
		refundConfigRecord.setC_InvoiceSchedule(invoiceSchedule);
		refundConfigRecord.setRefundBase(X_C_Flatrate_RefundConfig.REFUNDBASE_Percentage);
		refundConfigRecord.setRefundPercent(THREE);
		refundConfigRecord.setRefundMode(X_C_Flatrate_RefundConfig.REFUNDMODE_Accumulated);
		saveRecord(refundConfigRecord);

		final I_C_Flatrate_Term refundContractRecord = newInstance(I_C_Flatrate_Term.class);
		refundContractRecord.setType_Conditions(X_C_Flatrate_Term.TYPE_CONDITIONS_Refund);
		refundContractRecord.setBill_BPartner(bPartnerRecord);
		refundContractRecord.setC_Flatrate_Conditions(conditionsRecord);
		refundContractRecord.setM_Product(productRecord);
		refundContractRecord.setC_Currency(currencyRecord);
		refundContractRecord.setStartDate(TimeUtil.asTimestamp(RefundTestTools.CONTRACT_START_DATE));
		refundContractRecord.setEndDate(TimeUtil.asTimestamp(RefundTestTools.CONTRACT_END_DATE));
		save(refundContractRecord);

		final I_C_Invoice_Candidate refundContractIcRecord = newInstance(I_C_Invoice_Candidate.class);
		refundContractIcRecord.setBill_BPartner(bPartnerRecord);
		refundContractIcRecord.setM_Product(productRecord);
		refundContractIcRecord.setDateToInvoice(dateToInvoiceOfAssignableCand);
		refundContractIcRecord.setAD_Table_ID(getTableId(I_C_Flatrate_Term.class));
		refundContractIcRecord.setRecord_ID(refundContractRecord.getC_Flatrate_Term_ID());
		refundContractIcRecord.setC_Currency(currencyRecord);
		refundContractIcRecord.setPriceActual(TEN);
		save(refundContractIcRecord);

		assignmentRecord = newInstance(I_C_Invoice_Candidate_Assignment.class);
		assignmentRecord.setC_Invoice_Candidate_Assigned_ID(assignableIcRecord.getC_Invoice_Candidate_ID());
		assignmentRecord.setC_Flatrate_RefundConfig(refundConfigRecord);
		assignmentRecord.setC_Invoice_Candidate_Term_ID(refundContractIcRecord.getC_Invoice_Candidate_ID());
		save(assignmentRecord);

		final RefundConfigRepository refundConfigRepository = new RefundConfigRepository(new InvoiceScheduleRepository());

		final RefundContractRepository refundContractRepository = new RefundContractRepository(refundConfigRepository);

		final AssignmentAggregateService assignmentAggregateService = new AssignmentAggregateService(refundConfigRepository);

		final RefundInvoiceCandidateFactory refundInvoiceCandidateFactory = new RefundInvoiceCandidateFactory(
				refundContractRepository,
				assignmentAggregateService);

		final RefundInvoiceCandidateRepository refundInvoiceCandidateRepository = new RefundInvoiceCandidateRepository(
				refundContractRepository,
				refundInvoiceCandidateFactory);

		assignmentToRefundCandidateRepository = new AssignmentToRefundCandidateRepository(refundInvoiceCandidateRepository);
	}

	@Test
	public void ofRecord_AssignableInvoiceCandidate_no_assignment_record()
	{
		final AssignableInvoiceCandidateFactory assignableInvoiceCandidateFactory = new AssignableInvoiceCandidateFactory();
		final AssignableInvoiceCandidate assignableIc = assignableInvoiceCandidateFactory.ofRecord(assignableIcRecord);

		// guards
		final List<AssignmentToRefundCandidate> resultBeforeDeletion = assignmentToRefundCandidateRepository.getAssignmentsToRefundCandidate(assignableIc);
		assertThat(resultBeforeDeletion).isNotEmpty();

		delete(assignmentRecord);

		// invoke the method under test
		final List<AssignmentToRefundCandidate> resultAfterDeletion = assignmentToRefundCandidateRepository.getAssignmentsToRefundCandidate(assignableIc);
		assertThat(resultAfterDeletion).isEmpty();
	}
}
