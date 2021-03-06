package de.metas.contracts.refund;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.adempiere.model.InterfaceWrapperHelper.newInstance;
import static org.adempiere.model.InterfaceWrapperHelper.save;
import static org.adempiere.model.InterfaceWrapperHelper.saveRecord;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.test.AdempiereTestHelper;
import org.adempiere.util.time.SystemTime;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.compiere.util.TimeUtil;
import org.junit.Before;
import org.junit.Test;

import de.metas.adempiere.model.I_C_Currency;
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

public class AssignableInvoiceCandidateFactoryTest
{
	private I_C_BPartner bPartnerRecord;
	private I_M_Product productRecord;
	private I_C_Invoice_Candidate assignableIcRecord;
	private Timestamp dateToInvoiceOfAssignableCand;

	private AssignableInvoiceCandidateFactory assignableInvoiceCandidateFactory;

	private static final BigDecimal NINE = new BigDecimal("9");

	@Before
	public void init()
	{
		AdempiereTestHelper.get().init();

		bPartnerRecord = newInstance(I_C_BPartner.class);
		save(bPartnerRecord);

		final I_C_UOM uomRecord = newInstance(I_C_UOM.class);
		saveRecord(uomRecord);

		productRecord = newInstance(I_M_Product.class);
		productRecord.setC_UOM(uomRecord);
		save(productRecord);

		final I_C_Currency currencyRecord = newInstance(I_C_Currency.class);
		currencyRecord.setStdPrecision(2);
		save(currencyRecord);

		dateToInvoiceOfAssignableCand = SystemTime.asTimestamp();

		assignableIcRecord = newInstance(I_C_Invoice_Candidate.class);
		assignableIcRecord.setBill_BPartner(bPartnerRecord);
		assignableIcRecord.setM_Product(productRecord);
		assignableIcRecord.setDateToInvoice(dateToInvoiceOfAssignableCand);
		assignableIcRecord.setNetAmtInvoiced(ONE);
		assignableIcRecord.setNetAmtToInvoice(NINE);
		assignableIcRecord.setC_Currency(currencyRecord);
		save(assignableIcRecord);

		assignableInvoiceCandidateFactory = new AssignableInvoiceCandidateFactory();
	}

	@Test
	public void ofRecord_AssignableInvoiceCandidate()
	{
		// invoke the method under test
		final AssignableInvoiceCandidate ofRecord = assignableInvoiceCandidateFactory.ofRecord(assignableIcRecord);

		assertThat(ofRecord.getBpartnerId().getRepoId()).isEqualTo(bPartnerRecord.getC_BPartner_ID());
		assertThat(ofRecord.getProductId().getRepoId()).isEqualTo(productRecord.getM_Product_ID());

		// TODO move to dedicated test case
		// assertThat(cast.getAssignmentsToRefundCandidates().get(0).getRefundInvoiceCandidate().getId().getRepoId()).isEqualTo(refundContractIcRecord.getC_Invoice_Candidate_ID());
		assertThat(ofRecord.getMoney().getValue()).isEqualByComparingTo(TEN);
		assertThat(ofRecord.getInvoiceableFrom()).isEqualTo(TimeUtil.asLocalDate(dateToInvoiceOfAssignableCand));
	}
}
