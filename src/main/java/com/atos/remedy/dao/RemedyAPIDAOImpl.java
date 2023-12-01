package com.atos.remedy.dao;

import java.text.ParseException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import com.atos.remedy.model.DomainAccounts;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RemedyAPIDAOImpl implements RemedyAPIDAO
{
    Logger logger;
    @Autowired
    private DatabaseConnectionDao databaseconnectionDao;
    
    public RemedyAPIDAOImpl() {
        this.logger = LogManager.getLogger((Class)RemedyAPIDAOImpl.class);
    }
    
    @Override
    public List<DomainAccounts> listDomainAccounts() throws ParseException {
        final Connection conn = this.databaseconnectionDao.getConnection();
        ResultSet rst = null;
        CallableStatement cstmts = null;
        final List<DomainAccounts> domainUsers = new ArrayList<DomainAccounts>();
        try {
            this.logger.info("Stored Procedure dbo.GET_REMEDY_FULLDATA() being called");
            cstmts = conn.prepareCall("{call dbo.GET_REMEDY_FULLDATA()}");
            cstmts.executeQuery();
            rst = cstmts.getResultSet();
            this.logger.info("Call to dbo.GET_REMEDY_FULLDATA() is successful");
            while (rst.next()) {
                final DomainAccounts accounts = new DomainAccounts();
                accounts.setLoginID((rst.getString("LoginID") == null) ? "" : rst.getString("LoginID").toString());
                accounts.setDisplayName((rst.getString("DisplayName") == null) ? "" : rst.getString("DisplayName").toString());
                accounts.setBuilding((rst.getString("Building") == null) ? "" : rst.getString("Building").toString());
                accounts.setCompany((rst.getString("Company") == null) ? "" : rst.getString("Company").toString());
                accounts.setDomain((rst.getString("domain") == null) ? "" : rst.getString("domain").toString().toUpperCase());
                accounts.setEmployeeType((rst.getString("EmployeeType") == null) ? "" : rst.getString("EmployeeType").toString());
                if (rst.getString("EmployeeNumber") == null || rst.getString("EmployeeNumber").length() < 2 || !rst.getString("EmployeeNumber").matches("[0-9]+")) {
                    accounts.setEmployeeNumber("");
                }
                else {
                    accounts.setEmployeeNumber(String.format("%08d", Long.parseLong(rst.getString("EmployeeNumber").toString())));
                }
                accounts.setMail((rst.getString("Mail") == null) ? "" : rst.getString("Mail").toString());
                accounts.setManagerLoginID((rst.getString("Managerlogin") == null) ? "" : rst.getString("Managerlogin").toString());
                accounts.setStatus((rst.getString("userDXRState") == null) ? "" : rst.getString("userDXRState").toString());
                accounts.setAccountingCode((rst.getString("SLACode") == null) ? "" : rst.getString("SLACode").toString());
                accounts.setPrimaryCostCentreCode((rst.getString("SAPChargeCode") == null) ? "" : rst.getString("SAPChargeCode").toString());
                accounts.setJobTitle((rst.getString("Title") == null) ? "" : rst.getString("Title").toString());
                accounts.setTelephoneNumber((rst.getString("TelephoneNumber") == null) ? "" : rst.getString("TelephoneNumber").toString());
                accounts.setDepartment((rst.getString("Department") == null) ? "" : rst.getString("Department").toString());
                accounts.setDivision((rst.getString("Division") == null) ? "" : rst.getString("Division").toString());
                accounts.setFirstName((rst.getString("GivenName") == null) ? "" : rst.getString("GivenName").toString());
                accounts.setLastName((rst.getString("SN") == null) ? "" : rst.getString("SN").toString());
                accounts.setPreferredName((rst.getString("PreferredName") == null) ? "" : rst.getString("PreferredName").toString());
                if (rst.getString("OAC") == null || rst.getString("OAC").length() <= 0 || !rst.getString("OAC").matches("[0-9]+")) {
                    accounts.setOfficeAddressCode("000");
                }
                else {
                    accounts.setOfficeAddressCode(String.format("%03d", Integer.parseInt(rst.getString("OAC").toString())));
                }
                accounts.setRoomNumber((rst.getString("RoomNumber") == null) ? "" : rst.getString("RoomNumber").toString());
                accounts.setLocation((rst.getString("Location") == null) ? "" : rst.getString("Location").toString());
                accounts.setSubDivision1Name((rst.getString("SubDivision1Name") == null) ? "" : rst.getString("SubDivision1Name").toString());
                accounts.setSubDivision2Name((rst.getString("SubDivision2Name") == null) ? "" : rst.getString("SubDivision2Name").toString());
                accounts.setSubDivision3Name((rst.getString("SubDivision3Name") == null) ? "" : rst.getString("SubDivision3Name").toString());
                accounts.setSubDivision4Name((rst.getString("SubDivision4Name") == null) ? "" : rst.getString("SubDivision4Name").toString());
                domainUsers.add(accounts);
            }
        }
        catch (Exception e) {
            this.logger.error("Error occured while fetching data for FULL API", (Throwable)e);
            if (cstmts != null) {
                try {
                    cstmts.close();
                }
                catch (Exception e2) {
                    this.logger.error("Error occured while closing connection for FULL API", (Throwable)e2);
                }
                return domainUsers;
            }
            return domainUsers;
        }
        finally {
            if (cstmts != null) {
                try {
                    cstmts.close();
                }
                catch (Exception e2) {
                    this.logger.error("Error occured while closing connection for FULL API", (Throwable)e2);
                }
            }
        }
        if (cstmts != null) {
            try {
                cstmts.close();
            }
            catch (Exception e2) {
                this.logger.error("Error occured while closing connection for FULL API", (Throwable)e2);
            }
        }
        return domainUsers;
    }
}
