package de.metas.document.archive.spi.impl;

import static org.adempiere.model.InterfaceWrapperHelper.getTableId;
import static org.adempiere.model.InterfaceWrapperHelper.newInstance;
import static org.adempiere.model.InterfaceWrapperHelper.saveRecord;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.adempiere.test.AdempiereTestHelper;
import org.compiere.model.I_C_Invoice;
import org.junit.Before;
import org.junit.Test;

import de.metas.adempiere.model.I_AD_User;
import de.metas.document.archive.DocOutBoundRecipient;
import de.metas.document.archive.DocOutBoundRecipientRepository;
import de.metas.document.archive.model.I_C_Doc_Outbound_Log;
import de.metas.order.model.I_C_BPartner;

/*
 * #%L
 * de.metas.document.archive.base
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

public class InvoiceDocOutboundLogMailRecipientProviderTest
{
	private I_C_Doc_Outbound_Log docOutboundLogRecord;
	private InvoiceDocOutboundLogMailRecipientProvider invoiceDocOutboundLogMailRecipientProvider;
	private I_C_BPartner bPartnerRecord;

	@Before
	public void init()
	{
		AdempiereTestHelper.get().init();

		bPartnerRecord = newInstance(I_C_BPartner.class);
		saveRecord(bPartnerRecord);

		final I_C_Invoice invoiceRecord = newInstance(I_C_Invoice.class);
		invoiceRecord.setC_BPartner(bPartnerRecord);
		saveRecord(invoiceRecord);

		docOutboundLogRecord = newInstance(I_C_Doc_Outbound_Log.class);
		docOutboundLogRecord.setAD_Table_ID(getTableId(I_C_Invoice.class));
		docOutboundLogRecord.setRecord_ID(invoiceRecord.getC_Invoice_ID());
		saveRecord(docOutboundLogRecord);

		invoiceDocOutboundLogMailRecipientProvider = new InvoiceDocOutboundLogMailRecipientProvider(new DocOutBoundRecipientRepository());
	}

	@Test
	public void provideMailRecipient()
	{
		final I_AD_User userRecord2 = newInstance(I_AD_User.class);
		userRecord2.setEMail(null);
		userRecord2.setIsBillToContact_Default(true);
		userRecord2.setC_BPartner(bPartnerRecord);

		final I_AD_User userRecord = newInstance(I_AD_User.class);
		userRecord.setEMail("userRecord.EMail");
		userRecord.setC_BPartner(bPartnerRecord);
		saveRecord(userRecord);

		// invoke the method under test
		final Optional<DocOutBoundRecipient> result = invoiceDocOutboundLogMailRecipientProvider.provideMailRecipient(docOutboundLogRecord);
		assertThat(result).isPresent();
		assertThat(result.get().getEmailAddress()).isEqualTo("userRecord.EMail");
	}

}
