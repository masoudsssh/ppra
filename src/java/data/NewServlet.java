/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

/**
 *
 * @author Ghasemi
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"}, initParams = {
    @WebInitParam(name = "id", value = "Value")
    , @WebInitParam(name = "location", value = "Value")})
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String location = request.getParameter("location");
PrintWriter out = response.getWriter();
        try  {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Product Recommendation Application</title>");
            out.println("<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' type='text/css' rel='stylesheet' />");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
            out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js' type='text/javascript' ></script>");
            
            out.println("<script type=\"text/javascript\">" +
"\n " +
"    function loadImg(key, item){\n" +
" var keyword = 'apple'; \n " +
"        $.getJSON(\"http://api.flickr.com/services/feeds/photos_public.gne?jsoncallback=?\",\n" +
"        {\n" +
"            tags: keyword,\n" +
"            tagmode: \"all\",\n" +
"            format: \"json\"\n" +
"        },\n" +
"        function(data) {\n" +
"            var rnd = Math.floor(Math.random() * data.items.length);\n" +
"\n" +
"      var image_src = data.items[0]['media']['m'].replace(\"_m\", \"_b\");\n" +
"\n  console.log( image_src );  " +
"            item.attr('src', image_src );\n" +
"\n" +
"        });\n" +
"\n" +
"   }  </script>");
            
            out.println("</head>");
            out.println("<body><div class=\"container\">");
            //out.println("<a href="+'"'+"/WebApplication2/newjsp.jsp"+'"'+">Back</a>");
            //out.println("<h1>Product ID: " + id + " <br/> Location:  " + location + "</h1>");
            

            String Loc = "", DescSelected = "", Desc = "";

//					jbnButton1.setLabel("Type here Something"); 
            long startTime = System.currentTimeMillis();
            
            DataModel de = new FileDataModel(new File(getServletContext().getRealPath("/data/online_retail_new.csv"))); //movies.csv

            ItemSimilarity sim = new LogLikelihoodSimilarity(de);
//					TanimotoCoefficientSimilarity sim = new TanimotoCoefficientSimilarity(de);	 
//					PearsonCorrelationSimilarity sim = new PearsonCorrelationSimilarity(de);
//					EuclideanDistanceSimilarity sim = new EuclideanDistanceSimilarity(de);
//					UncenteredCosineSimilarity sim = new UncenteredCosineSimilarity(de);

            GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(de, sim);

            int x = Integer.parseInt(id);

            //BufferedReader br = new BufferedReader(new FileReader("/online_retail_new.csv"));
           // BufferedReader br = new BufferedReader(new FileReader(getClass().getResourceAsStream("online_retail_new.csv").toString()));
          //  BufferedReader br = new BufferedReader(new FileReader());
            InputStreamReader isr = new InputStreamReader(getServletContext().getResourceAsStream("/data/online_retail_new.csv"));
            BufferedReader br = new BufferedReader(isr);
            String line;

            for (LongPrimitiveIterator items = de.getItemIDs(); items.hasNext();) {
                long itemId = items.nextLong();
                if (itemId == x) {
                    List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemId, 10);

                    String str = "<tr>"
                            + "<th style='text-align:left'>ProductID</th>"
                            + "<th style='text-align:left'>Similarity</th>"
                            + "<th style='text-align:left'>Description</th>"
                            + "<th style='text-align:left'>Location</th>"
                            + "</tr>";
                    recommendations = Lists.reverse(recommendations);
                    for (RecommendedItem recommendation : recommendations) {
                        while ((line = br.readLine()) != null) {
                            String[] values = line.split(",", -1);
                            if (values[1].equals(recommendation.getItemID() + "")) {
                                Desc = values[4];
                                Loc = values[5];
                                break;
                            }

                            if (values[1].equals(itemId + "")) {
                                DescSelected = values[4];
                                System.out.println(DescSelected);
                            }
                        }

                        str += "<tr><td><input type='hidden' value='"+Desc+"' class='proname'>" + recommendation.getItemID() + "</td><td>" + recommendation.getValue()
                                + "</td><td>" + Desc + "<img style='width:100px' src='' id='"+recommendation.getItemID()+"' /><script>loadImg('"+Desc+"', $('#"+recommendation.getItemID()+"') )</script></td><td>" + Loc + "</td></tr>";

                        System.out.println(itemId + "," + recommendation.getItemID() + "," + recommendation.getValue());
                    }
                    long endTime = System.currentTimeMillis();
                    long runtime = endTime - startTime;
                    System.out.println(runtime + " millisecond");
                    out.println("<br/><table class='table'><th colspan='4' style='background-color:black; color:white'>Recommended Products"+"<a href="+'"'+"/WebApplication2/newjsp.jsp"+'"'+" class='pull-right'>Back</a>"+"</th>" + str + "</table><br/><strong>Product ID: </strong>" + itemId + " <br/><strong>Description: </strong> " + DescSelected + "<br/> <strong>Runtime: </strong>" + runtime + " millisecond");
                    out.println("</div></body>");
            out.println("</html>");
                }
            }

        } catch (IOException e1) {
            out.println("There was an error." + e1);
            e1.printStackTrace();
        } catch (TasteException e2) {
            out.println("There was a Taste exception." + e2);
            e2.printStackTrace();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
