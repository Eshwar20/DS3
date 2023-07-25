package com.ull.DS3;

public class Search {
        
    private String query;
    private String results;
    
    public Search(String query, String results) {
        this.query = query;
        this.results = results;
    }
    
    // getters and setters
    public String getQuery() {
        return query;
    }
    
    public void setQuery(String query) {
        this.query = query;
    }
    
    public String getResults() {
        return results;
    }
    
    public void setResults(String results) {
        this.results = results;
    }
    
}
