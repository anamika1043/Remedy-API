package com.atos.remedy.dao;

import java.text.ParseException;
import com.atos.remedy.model.DomainAccounts;
import java.util.List;

public interface RemedyAPIDAO
{
    List<DomainAccounts> listDomainAccounts() throws ParseException;
}
