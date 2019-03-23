<%-- 
    Document   : history
    Created on : 14-Mar-2019, 15:54:08
    Author     : Pandu
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="models.Overtime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean cekData = session.getAttribute("history") != null;
        Overtime ov = (cekData) ? (Overtime) session.getAttribute("overtime") : null;
        boolean cekList = session.getAttribute("history") != null;
        if (!cekList) {
            response.sendRedirect("./HistoryServlet");
        }
    %>


    <div class="section__content section__content--p30">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                </div>
            </div>
            <div class="row m-t-25">
            </div>
            <div class="row">
                <div class="col-lg-12">

                    <div class="row m-t-45">
                    </div>
                    <div class="au-card recent-report">
                        <div class="au-card-inner">
                            <div class="col-lg-12">
                                <h3 class="title-10" > History</h3>
                                <div class="row m-t-25">
                                </div>
                                <div class="table-responsive table--no-card m-b-30">
                                    <!--TABLE HERE-->
                                    <table id="historyTable" class="table table-borderless table-striped table-earning" cellspacing='30' align ='center'>
                                        <thead>
                                            <tr>
                                                <th>No.</th>
                                                <th>Date</th>
                                                <!--<th>Duration</th>-->
                                                <th>Time Sheet</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% int i = 1;
                                                if (cekList) {
                                                    for (Overtime elem : (List<Overtime>) session.getAttribute("history")) {%>
                                            <tr>
                                                <td><%= i++%></td>
                                                <!--<td><--%= // elem.getId()%></td>-->
                                                <td><%= sdf.format(elem.getDate())%></td>
                                                <!--<td><--%= // elem.getTimeduration()%></td>-->
                                                <!--<td><--%= // elem.getKeterangan()%></td>-->
                                                <td><%= elem.getTimesheet().getId()%></td>
                                                <td><%= elem.getStatus().getStatus()%></td>
                                            </tr>
                                            <%}
                                                } else {
                                                    response.sendRedirect("./HistoryServlet");
                                                }%>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="row m-t-25">
                                </div>

                                <div class="chart-note">
                                    <button class="btnhistory au-btn au-btn-icon au-btn--blue">
                                        <i class="zmdi"></i>PRINT</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <script>
        $('.btnhistory').click(function () {
            var printme = document.getElementById('historyTable');
            var wme = window.open("", "", "width=900,height=700");
            wme.document.write(printme.outerHTML);
            wme.document.close();
            wme.focus();
            wme.print();
            wme.close();
        })
    </script>
</html>
