
<%@page import="org.apache.mahout.cf.taste.common.TasteException"%>
<%@page import="java.io.IOException"%>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="com.google.common.collect.Lists"%>
<%@page import="org.apache.mahout.cf.taste.recommender.RecommendedItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender"%>
<%@page import="org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender"%>
<%@page import="org.apache.mahout.cf.taste.similarity.ItemSimilarity"%>
<%@page import="org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity"%>
<%@page import="org.apache.mahout.cf.taste.model.DataModel"%>
<%@page import="org.apache.mahout.cf.taste.impl.model.file.FileDataModel"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript" ></script>
        <title>Product Recommendation Application</title>
    </head>
    <body>
        <div class="container">
            <h1>Product Recommendation Application</h1>
            <div class="row">
                <div class="col-md-12"><h3>Enter the product details</h3></div>
            </div>
            <br/>
            
            <form action="NewServlet" method="GET">
                <div class="row">
                    <div class="col-md-2">
                        Product ID: 
                    </div>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="id" value="22556">
                    </div>
                </div>
                <br/>
                
                <div class="row">
                    <div class="col-md-2">
                        Location: 
                    </div>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="location" value="Switzerland"/>
                    </div>
                </div>
                <br/>
                
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-10">
                        <input type="submit" value="Recommend" class="btn btn-success pull-right" />
                        <br/>
                    </div>
                </div>
            </form>
        <br/>
        <%

//					jbnButton1.setLabel("Type here Something"); 
            long startTime = System.currentTimeMillis();
            String Loc = "", DescSelected = "", Desc = "";

            InputStreamReader isr = new InputStreamReader(getServletContext().getResourceAsStream("/data/online_retail_new.csv"));
            BufferedReader br = new BufferedReader(isr);
            String line;
            String cvsSplitBy = ",";
            Integer pageNo = 0;
            try {
                String str = "<table class='table'> <tr>"
                        + "<th style='text-align:left'>ProductID</th>"
                        + "<th style='text-align:left'>Description</th>"
                        + "<th style='text-align:left'>Location</th>"
                        + "</tr>";
                while ((line = br.readLine()) != null) {
                    pageNo++;
                    // use comma as separator
                    String[] data = line.split(cvsSplitBy);

                    str += "<tr> <td> <a href='/WebApplication2/NewServlet?id="+data[1]+"&location="+data[5]+"'>" + data[1] + "</a></td><td> " + data[4] + " </td> <td> " + data[5] + " </td> </tr>";
                    if(pageNo>20){ break; }
                }
str += "</table>";
                out.print(str);
            } catch (FileNotFoundException e) {
                out.print("" + e);
                e.printStackTrace();
            } catch (IOException e) {
                  out.print("" + e);
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                          out.print("" + e);
                        e.printStackTrace();
                    }
                }
            }


        %>
        
        </div>
    </body>
</html>
