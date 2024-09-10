/*
Question
Web tracking. Suppose that you are tracking n web sites and m users and you want to 
support the following API:

* User visits a website.
* How many times has a given user visited a given site?

What data structure or data structures would you use?
*/

import java.util.HashMap;
import java.util.Map;

public class WebTracking {
    /*
     * Nested Hashmaps to store the visits
     * outer map key: userID, value: inner map
     * Inner map key: siteID, valaue: visit count
     */
    private Map<String, Map<String, Integer>> userVisits;

    // constructor
    public WebTracking() {
        userVisits = new HashMap<>();
    }

    // Method to record the user's visit to the website
    public void visitSite(String userId, String siteId) {
        // Retrieve the user's map of site visits or create a new one if it doesn't exist
        userVisits.putIfAbsent(userId, new HashMap<>());
        Map<String, Integer> siteVisits = userVisits.get(userId);

        // increment the visit count of site: if no previous visits, initialized to 1
        siteVisits.put(siteId, siteVisits.getOrDefault(siteId, 0)+1);
    }

    // Method to get the number of times a user has visited a specific site
    public int getVisitCount(String userId, String siteId) {
        // check if the user exist in the map
        if (!userVisits.containsKey(userId)) return 0; // user not found

        // get the site visit count for the user; if the site has no visits, return 0
        Map<String, Integer> siteVisits = userVisits.get(userId);
        return siteVisits.getOrDefault(siteId, 0);
    }

    // Main method to demonstrate the usage
    public static void main(String[] args) {
        WebTracking tracker = new WebTracking();

        // Simulate user visits
        tracker.visitSite("user1", "siteA");
        tracker.visitSite("user1", "siteA");
        tracker.visitSite("user1", "siteB");
        tracker.visitSite("user2", "siteA");

        // Query visit counts
        System.out.println("User1 visits to SiteA: " + tracker.getVisitCount("user1", "siteA")); // Output: 2
        System.out.println("User1 visits to SiteB: " + tracker.getVisitCount("user1", "siteB")); // Output: 1
        System.out.println("User2 visits to SiteA: " + tracker.getVisitCount("user2", "siteA")); // Output: 1
        System.out.println("User2 visits to SiteB: " + tracker.getVisitCount("user2", "siteB")); // Output: 0
    }
}
