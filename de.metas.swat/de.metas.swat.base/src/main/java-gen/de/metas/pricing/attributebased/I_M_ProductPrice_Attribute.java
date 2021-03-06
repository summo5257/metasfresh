/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package de.metas.pricing.attributebased;


/** Generated Interface for M_ProductPrice_Attribute
 *  @author Adempiere (generated) 
 */
@SuppressWarnings("javadoc")
public interface I_M_ProductPrice_Attribute 
{

    /** TableName=M_ProductPrice_Attribute */
    public static final String Table_Name = "M_ProductPrice_Attribute";

    /** AD_Table_ID=540523 */
//    public static final int Table_ID = org.compiere.model.MTable.getTable_ID(Table_Name);

//    org.compiere.util.KeyNamePair Model = new org.compiere.util.KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
//    java.math.BigDecimal accessLevel = java.math.BigDecimal.valueOf(3);

    /** Load Meta Data */

	/**
	 * Get Mandant.
	 * Mandant für diese Installation.
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getAD_Client_ID();

	public org.compiere.model.I_AD_Client getAD_Client();

    /** Column definition for AD_Client_ID */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_AD_Client> COLUMN_AD_Client_ID = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_AD_Client>(I_M_ProductPrice_Attribute.class, "AD_Client_ID", org.compiere.model.I_AD_Client.class);
    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/**
	 * Set Sektion.
	 * Organisatorische Einheit des Mandanten
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setAD_Org_ID (int AD_Org_ID);

	/**
	 * Get Sektion.
	 * Organisatorische Einheit des Mandanten
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getAD_Org_ID();

	public org.compiere.model.I_AD_Org getAD_Org();

	public void setAD_Org(org.compiere.model.I_AD_Org AD_Org);

    /** Column definition for AD_Org_ID */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_AD_Org> COLUMN_AD_Org_ID = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_AD_Org>(I_M_ProductPrice_Attribute.class, "AD_Org_ID", org.compiere.model.I_AD_Org.class);
    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/**
	 * Set Attribute_Line_Included_Tab.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public void setAttribute_Line_Included_Tab (java.lang.String Attribute_Line_Included_Tab);

	/**
	 * Get Attribute_Line_Included_Tab.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public java.lang.String getAttribute_Line_Included_Tab();

    /** Column definition for Attribute_Line_Included_Tab */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_Attribute_Line_Included_Tab = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "Attribute_Line_Included_Tab", null);
    /** Column name Attribute_Line_Included_Tab */
    public static final String COLUMNNAME_Attribute_Line_Included_Tab = "Attribute_Line_Included_Tab";

	/**
	 * Set Merkmale.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true
	 */
	public void setAttributeValues (java.lang.String AttributeValues);

	/**
	 * Get Merkmale.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true
	 */
	public java.lang.String getAttributeValues();

    /** Column definition for AttributeValues */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_AttributeValues = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "AttributeValues", null);
    /** Column name AttributeValues */
    public static final String COLUMNNAME_AttributeValues = "AttributeValues";

	/**
	 * Get Erstellt.
	 * Datum, an dem dieser Eintrag erstellt wurde
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public java.sql.Timestamp getCreated();

    /** Column definition for Created */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_Created = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "Created", null);
    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/**
	 * Get Erstellt durch.
	 * Nutzer, der diesen Eintrag erstellt hat
	 *
	 * <br>Type: Table
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getCreatedBy();

    /** Column definition for CreatedBy */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_AD_User> COLUMN_CreatedBy = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_AD_User>(I_M_ProductPrice_Attribute.class, "CreatedBy", org.compiere.model.I_AD_User.class);
    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/**
	 * Set Aktiv.
	 * Der Eintrag ist im System aktiv
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setIsActive (boolean IsActive);

	/**
	 * Get Aktiv.
	 * Der Eintrag ist im System aktiv
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public boolean isActive();

    /** Column definition for IsActive */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_IsActive = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "IsActive", null);
    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/**
	 * Set Standard.
	 * Default value
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setIsDefault (boolean IsDefault);

	/**
	 * Get Standard.
	 * Default value
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public boolean isDefault();

    /** Column definition for IsDefault */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_IsDefault = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "IsDefault", null);
    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/**
	 * Set Attribute price.
	 *
	 * <br>Type: ID
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setM_ProductPrice_Attribute_ID (int M_ProductPrice_Attribute_ID);

	/**
	 * Get Attribute price.
	 *
	 * <br>Type: ID
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getM_ProductPrice_Attribute_ID();

    /** Column definition for M_ProductPrice_Attribute_ID */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_M_ProductPrice_Attribute_ID = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "M_ProductPrice_Attribute_ID", null);
    /** Column name M_ProductPrice_Attribute_ID */
    public static final String COLUMNNAME_M_ProductPrice_Attribute_ID = "M_ProductPrice_Attribute_ID";

	/**
	 * Set Produkt-Preis.
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setM_ProductPrice_ID (int M_ProductPrice_ID);

	/**
	 * Get Produkt-Preis.
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getM_ProductPrice_ID();

	public org.compiere.model.I_M_ProductPrice getM_ProductPrice();

	public void setM_ProductPrice(org.compiere.model.I_M_ProductPrice M_ProductPrice);

    /** Column definition for M_ProductPrice_ID */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_M_ProductPrice> COLUMN_M_ProductPrice_ID = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_M_ProductPrice>(I_M_ProductPrice_Attribute.class, "M_ProductPrice_ID", org.compiere.model.I_M_ProductPrice.class);
    /** Column name M_ProductPrice_ID */
    public static final String COLUMNNAME_M_ProductPrice_ID = "M_ProductPrice_ID";

	/**
	 * Set Mindestpreis.
	 * Unterster Preis für Kostendeckung
	 *
	 * <br>Type: CostPrice
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public void setPriceLimit (java.math.BigDecimal PriceLimit);

	/**
	 * Get Mindestpreis.
	 * Unterster Preis für Kostendeckung
	 *
	 * <br>Type: CostPrice
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public java.math.BigDecimal getPriceLimit();

    /** Column definition for PriceLimit */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_PriceLimit = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "PriceLimit", null);
    /** Column name PriceLimit */
    public static final String COLUMNNAME_PriceLimit = "PriceLimit";

	/**
	 * Set Auszeichnungspreis.
	 * Auszeichnungspreis
	 *
	 * <br>Type: CostPrice
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public void setPriceList (java.math.BigDecimal PriceList);

	/**
	 * Get Auszeichnungspreis.
	 * Auszeichnungspreis
	 *
	 * <br>Type: CostPrice
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public java.math.BigDecimal getPriceList();

    /** Column definition for PriceList */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_PriceList = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "PriceList", null);
    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/**
	 * Set Standardpreis.
	 * Standardpreis
	 *
	 * <br>Type: CostPrice
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public void setPriceStd (java.math.BigDecimal PriceStd);

	/**
	 * Get Standardpreis.
	 * Standardpreis
	 *
	 * <br>Type: CostPrice
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public java.math.BigDecimal getPriceStd();

    /** Column definition for PriceStd */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_PriceStd = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "PriceStd", null);
    /** Column name PriceStd */
    public static final String COLUMNNAME_PriceStd = "PriceStd";

	/**
	 * Set Reihenfolge.
	 * Zur Bestimmung der Reihenfolge der Einträge;
 die kleinste Zahl kommt zuerst
	 *
	 * <br>Type: Integer
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setSeqNo (int SeqNo);

	/**
	 * Get Reihenfolge.
	 * Zur Bestimmung der Reihenfolge der Einträge;
 die kleinste Zahl kommt zuerst
	 *
	 * <br>Type: Integer
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getSeqNo();

    /** Column definition for SeqNo */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_SeqNo = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "SeqNo", null);
    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/**
	 * Get Aktualisiert.
	 * Datum, an dem dieser Eintrag aktualisiert wurde
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public java.sql.Timestamp getUpdated();

    /** Column definition for Updated */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object> COLUMN_Updated = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, Object>(I_M_ProductPrice_Attribute.class, "Updated", null);
    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/**
	 * Get Aktualisiert durch.
	 * Nutzer, der diesen Eintrag aktualisiert hat
	 *
	 * <br>Type: Table
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getUpdatedBy();

    /** Column definition for UpdatedBy */
    public static final org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_AD_User> COLUMN_UpdatedBy = new org.adempiere.model.ModelColumn<I_M_ProductPrice_Attribute, org.compiere.model.I_AD_User>(I_M_ProductPrice_Attribute.class, "UpdatedBy", org.compiere.model.I_AD_User.class);
    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";
}
