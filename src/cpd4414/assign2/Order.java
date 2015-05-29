/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 * Updated 2015 Mark Russell <mark.russell@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cpd4414.assign2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class Order {
    private String customerId;
    private String customerName;
    private Date timeReceived;
    private Date timeProcessed;
    private Date timeFulfilled;
    private List<Purchase> listOfPurchases = new ArrayList<>();
    private String notes;
    
    public Order(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }
    
    public void addPurchase(Purchase p) {
        listOfPurchases.add(p);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(Date timeReceived) {
        this.timeReceived = timeReceived;
    }

    public Date getTimeProcessed() {
        return timeProcessed;
    }

    public void setTimeProcessed(Date timeProcessed) {
        this.timeProcessed = timeProcessed;
    }

    public Date getTimeFulfilled() {
        return timeFulfilled;
    }

    public void setTimeFulfilled(Date timeFulfilled) {
        this.timeFulfilled = timeFulfilled;
    }

    public List<Purchase> getListOfPurchases() {
        return listOfPurchases;
    }

    public void setListOfPurchases(List<Purchase> listOfPurchases) {
        this.listOfPurchases = listOfPurchases;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public JSONObject toJSON() {
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("customerId", getCustomerId());
        jsonobj.put("customerName", getCustomerName());
        jsonobj.put("timeReceived", (getTimeReceived() != null) ? getTimeReceived().toString() : null);
        jsonobj.put("timeProcessed", (getTimeProcessed() != null) ? getTimeProcessed().toString() : null);
        jsonobj.put("timeReceived", (getTimeFulfilled() != null) ? getTimeFulfilled().toString() : null);
        JSONArray jsonArr = new JSONArray();
        for (Purchase purchase : getListOfPurchases()) {
            JSONObject purchaseObj = new JSONObject();
            purchaseObj.put("productId", purchase.getProductId());
            purchaseObj.put("quantity", purchase.getQuantity());
            jsonArr.add(purchaseObj);
        }
        jsonobj.put("purchases", jsonArr);
        jsonobj.put("notes", getNotes());
        return jsonobj;
    }
            
}
