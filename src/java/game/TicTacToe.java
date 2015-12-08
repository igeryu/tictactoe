package game;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alan Johnson
 */
public class TicTacToe extends HttpServlet {
    
    private Game game = new Game();

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
        
        String newGame = request.getParameter("reset");
        
        if (newGame == null) {
            
            String spaceX = request.getParameter("spaceX");
            String spaceY = request.getParameter("spaceY");
            
            if (spaceX != null && spaceY != null) {
                
                int i = Integer.valueOf(spaceX);
                int j = Integer.valueOf(spaceY);
                
                game.setSpace(i, j);
            }
            
        } else if (newGame.equals("true")) {
            game.newGame();
        }
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Alan Johnson - CMIS 315 - Project 1 - TicTacToe</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<br/>");
            
            String statusMessage = "";
            if (game.status == Game.Status.Playing) {

                switch (game.turn) {
                    case Player_1:
                        statusMessage = "Player 1's Turn (X)";
                        break;

                    case Player_2:
                        statusMessage = "Player 2's Turn (O)";
                        break;
                }

            } else {

                switch (game.status) {

                    case Winner_1:
                        statusMessage = "Player 1 Won!";
                        break;

                    case Winner_2:
                        statusMessage = "Player 2 Won!";
                        break;
                        
                    case Tied:
                        statusMessage = "Game Draw";
                        break;

                }

            }
            
            out.println("<center><h1>" + statusMessage + "</h1></center>");
            
            out.println("<br/><br/><br/><br/>");
            out.println("<table border=\"1\" cellspacing=\"10\" cellpadding=\"10\" align=\"center\">");
            
            Game.Space space[][] = game.spaces;
            String spaceString[][] = new String[3][3];
            
            for (int i = 0; i < 3; i++) {
                
                for (int j = 0; j < 3; j++) {
                    
                    switch (space[i][j]) {
                        
                        case Empty:
                            if (game.status == Game.Status.Playing)
                                spaceString[i][j] = "<form name=\"space_" + i + j + "\" action=\"TicTacToe\" method=\"POST\"/>"
                                                  + "<input type=\"submit\" value=\"Claim\">"
                                                  + "<input type=\"hidden\" name=\"spaceX\" value=\"" + i + "\"/>"
                                                  + "<input type=\"hidden\" name=\"spaceY\" value=\"" + j + "\"/>"
                                                  + "</form>";
                            else spaceString[i][j] = "";
                            break;
                            
                        case X:
                            spaceString[i][j] = "X";
                            break;
                            
                        case O:
                            spaceString[i][j] = "O";
                            break;
                        
                    }
                    
                }
                
            }
            
            //  Top Row:
            out.println("<tr><td height=\"50\" width=\"50\" align=\"center\">" + spaceString[0][0] + "</td>"
                          + "<td height=\"50\" width=\"50\" align=\"center\">" + spaceString[1][0] + "</td>"
                          + "<td height=\"50\" width=\"50\" align=\"center\">" + spaceString[2][0] + "</td>");
            
            //  Middle Row:
             out.println("<tr><td height=\"50\" width=\"50\" align=\"center\">" + spaceString[0][1] + "</td>"
                          + "<td height=\"50\" width=\"50\" align=\"center\">" + spaceString[1][1] + "</td>"
                          + "<td height=\"50\" width=\"50\" align=\"center\">" + spaceString[2][1] + "</td>");
            
            //  Bottom Row:
             out.println("<tr><td height=\"50\" width=\"50\" align=\"center\">" + spaceString[0][2] + "</td>"
                          + "<td height=\"50\" width=\"50\" align=\"center\">" + spaceString[1][2] + "</td>"
                          + "<td height=\"50\" width=\"50\" align=\"center\">" + spaceString[2][2] + "</td>");
            
            out.println("</table>");
            
            
            out.println("<br/><br/>");
            out.println("<form name=\"resetForm\" action=\"TicTacToe\" method=\"POST\" align=\"center\">\n" +
                        "<input name=\"reset\" type=\"hidden\" value=\"true\" />" +
                        "<input type=\"submit\" value=\"New Game\" />\n" +
                        "</form>");
            
            out.println("</body>");
            out.println("</html>");
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
