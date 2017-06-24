package org.launchcode.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String search(Model model, HttpServletRequest request)   {
        model.addAttribute("columns", ListController.columnChoices);

        String searchType = request.getParameter("searchType");
        String searchTerm = request.getParameter("searchTerm");
        ArrayList<HashMap<String, String>> allJobs = JobData.findAll();
        ArrayList<HashMap<String, String>> searchedJobs = new ArrayList<>();

        if (searchType.equals("all"))   {
            searchedJobs = JobData.findByValue(searchTerm);
        }   else    {
            searchedJobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        int Entries = 0;
        for (HashMap<String, String> number : searchedJobs) {
            Entries = Entries + 1;
        }
        model.addAttribute("jobs", searchedJobs);
        model.addAttribute("Entries", (Entries + "Result(s)"));
        return "search";
    }

}
