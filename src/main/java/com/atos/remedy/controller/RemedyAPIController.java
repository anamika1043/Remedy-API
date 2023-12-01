package com.atos.remedy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.atos.remedy.dao.RemedyAPIDAOImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/V2" })
public class RemedyAPIController
{
    @Autowired
    private RemedyAPIDAOImpl remedyAPIDAO;
    Logger logger;
    
    public RemedyAPIController() {
        this.logger = LogManager.getLogger((Class)RemedyAPIController.class);
    }
    
    @GetMapping(path = { "/FULL" }, produces = { "application/json" })
    public String getAllAccounts() {
        try {
            this.logger.info("FULL API is called!");
            String JsonValue = "";
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
            JsonValue = mapper.writerWithDefaultPrettyPrinter().writeValueAsString((Object)this.remedyAPIDAO.listDomainAccounts());
            JsonValue = "{\"users\": " + JsonValue + "}";
            return JsonValue;
        }
        catch (JsonGenerationException e) {
            this.logger.error("Error occured while fetching the data for FULL API", (Throwable)e);
        }
        catch (JsonMappingException e2) {
            this.logger.error("Error occured while fetching the data for FULL API", (Throwable)e2);
        }
        catch (IOException e3) {
            this.logger.error("Error occured while fetching the data for FULL API", (Throwable)e3);
        }
        catch (Exception e4) {
            this.logger.error("Error occured while fetching the data for FULL API", (Throwable)e4);
        }
        this.logger.error("No Data returned1");
        return "No data found!";
    }
}
