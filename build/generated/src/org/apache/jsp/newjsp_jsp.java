package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.mahout.cf.taste.common.TasteException;
import java.io.IOException;
import java.io.*;
import java.util.*;
import com.google.common.collect.Lists;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import java.util.List;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import java.io.FileReader;
import java.io.BufferedReader;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import java.io.File;

public final class newjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" type=\"text/css\" rel=\"stylesheet\" />\n");
      out.write("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" type=\"text/javascript\" ></script>\n");
      out.write("        <title>Product Recommendation Application</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <h1>Product Recommendation Application</h1>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-md-12\"><h3>Enter the product details</h3></div>\n");
      out.write("            </div>\n");
      out.write("            <br/>\n");
      out.write("            \n");
      out.write("            <form action=\"NewServlet\" method=\"GET\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-2\">\n");
      out.write("                        Product ID: \n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-10\">\n");
      out.write("                        <input type=\"text\" class=\"form-control\" name=\"id\" value=\"22556\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <br/>\n");
      out.write("                \n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-2\">\n");
      out.write("                        Location: \n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-10\">\n");
      out.write("                        <input type=\"text\" class=\"form-control\" name=\"location\" value=\"Switzerland\"/>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <br/>\n");
      out.write("                \n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-2\"></div>\n");
      out.write("                    <div class=\"col-md-10\">\n");
      out.write("                        <input type=\"submit\" value=\"Recommend\" class=\"btn btn-success pull-right\" />\n");
      out.write("                        <br/>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        <br/>\n");
      out.write("        ");


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


        
      out.write("\n");
      out.write("        \n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
