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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class OrderQueue {
    Queue<Order> orderQueue = new ArrayDeque<>();
    List<Order> orderList = new ArrayList();
    
     public void add(Order order) throws NoCustomerException, NoPurchasesException{
        if (order.getCustomerId().isEmpty() && order.getCustomerName().isEmpty()){
            throw new NoCustomerException();
        }
        if (order.getListOfPurchases().isEmpty()){
            throw new NoPurchasesException();
        }
        orderQueue.add(order);
        order.setTimeReceived(new Date());
    }
     
     public Order next(){
        return orderQueue.peek();
    }
     
     void Process(Order next) throws NoTimeReceivedException,NoTimeProcessedException{
        if(next.equals(next())){
            orderList.add(orderQueue.remove());
            next.setTimeProcessed(new Date());
        }else if(next.getTimeReceived() == null){
            throw new NoTimeReceivedException();
        }
    }
     
      void fulfill(Order next) throws NoTimeReceivedException, NoTimeProcessedException{
        if(next.getTimeReceived() == null){
            throw new NoTimeReceivedException();
        }
        if(next.getTimeProcessed() == null){
            throw new NoTimeProcessedException();
        }
        if(orderList.contains(next)){
            next.setTimeFulfilled(new Date());
        }
    }
      
      String report(){
        String result = "";
        if(!(orderQueue.isEmpty() && orderList.isEmpty())){
            JSONObject jsonobj = new JSONObject();
            JSONArray jsonarr = new JSONArray();
            
            for(Order i : orderList){
                jsonarr.add(i.toJSON());
            }
            
            for(Order i : orderQueue){
                jsonarr.add(i.toJSON());
            }
            
            jsonobj.put("orderArray",jsonarr);
            result = jsonobj.toJSONString();
        }
        return result;
    }

    public static class NoCustomerException extends Exception {

        public NoCustomerException() {
             super("There are not any customers.");
        }
    }

    public static class NoPurchasesException extends Exception {

        public NoPurchasesException() {
            super("There are not any purchases.");
        }
    }

    public static class NoTimeReceivedException extends Exception {

        public NoTimeReceivedException() {
            super("Time is not received on this order.");
        }
    }

    public static class NoTimeProcessedException extends Exception {

        public NoTimeProcessedException() {
            super("Time is not processed on this order.");
        }
    }
   
}
