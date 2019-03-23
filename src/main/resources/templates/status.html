<%-- 
    Document   : status
    Created on : 14-Mar-2019, 11:56:35
    Author     : Pandu
--%>

<%@page import="models.Overtime"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%boolean cekData = session.getAttribute("data") != null;
        Overtime ov = (cekData) ? (Overtime) session.getAttribute("overtime") : null;
        boolean cekList = session.getAttribute("data") != null;
        if (!cekList) {
            response.sendRedirect("./HistoryServlet");
        }
    %>

    <form action="StatusServlet" method="POST">
        <div class="modal fade" id="modalStatus" tabindex="-1" 
             role="dialog" aria-labeledby="myModalHeader" aris-hidden="true">
            <div class="modal-dialog" role="document" >
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h3 class="modal-title">EDITOR</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">ID</label>
                            <input type="text" id="SotId" name="SotId" class="form-control" value=<%= (session.getAttribute("otId") != null) ? session.getAttribute("otId") : ""%>>
                        </div>
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">Date</label>
                            <input type="date" id="Sotdate" name="Sotdate" class="form-control" value=<%= (session.getAttribute("otDate") != null) ? session.getAttribute("otDate") : ""%>>
                        </div>
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">Duration</label>
                            <input type="text" id="Sotduration" name="Sotduration" class="form-control" value=<%= (session.getAttribute("otDuration") != null) ? session.getAttribute("otDuration") : ""%>>
                        </div>
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">Description</label>
                            <input type="text" id="Sotdesc" name="Sotdesc" class="form-control" value=<%= (session.getAttribute("otDesc") != null) ? session.getAttribute("otDesc") : ""%>>
                        </div>
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">Time Sheet</label>
                            <input type="text" id="Stimesheet" name="Stimesheet" class="form-control" value=<%= (session.getAttribute("timesheet") != null) ? session.getAttribute("timesheet") : ""%>>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" type="submit" value="Save" name="save" >SAVE CHANGE</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
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
                                <h3 class="title-10" > Status</h3>
                                <div class="row m-t-25">
                                </div>
                                <div class="table-responsive table--no-card m-b-30">
                                    <!--TABLE HERE-->

                                    <table id="statusTable" class="table table-borderless table-striped table-earning" cellspacing='30' align ='center'>
                                        <thead>
                                            <tr>
                                                <th>No.</th>
                                                <!--<th>Id</th>-->
                                                <th>Date</th>
                                                <th>Duration</th>
                                                <!--<th>Keterangan</th>-->
                                                <!--<th>Time Sheet</th>-->
                                                <!--<th>Status</th>-->
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% int i = 1;
                                                if (cekList) {
                                                    for (Overtime elem : (List<Overtime>) session.getAttribute("data")) {%>
                                            <tr>
                                                <td><%= i++%></td>
                                                <!--<td><--%= // elem.getId()%></td>-->
                                                <td><%= elem.getDate()%></td>
                                                <td><%= elem.getTimeduration()%></td>
                                                <!--<td><--%=  elem.getKeterangan()%></td>-->
                                                <!--<td><--%= // elem.getTimesheet().getId()%></td>-->
                                                <!--<td><--%= // elem.getStatus().getStatus()%></td>-->
                                                <td>
                                                    <% if (!elem.getStatus().getId().equals("STA02")) {%>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalStatus" 
                                                            data-getid="<%= elem.getId()%>" 
                                                            data-getdate="<%= elem.getDate()%>" 
                                                            data-getduration="<%= elem.getTimeduration()%>" 
                                                            data-getdescription="<%= elem.getKeterangan()%>" 
                                                            data-getts="<%= elem.getTimesheet().getId()%>"
                                                            data-getstatus="<%= elem.getStatus().getStatus()%>" 
                                                            ><i class="fas fa-edit"></i></button>
                                                    <button type="button" class="btn btn-danger" href="StatusServlet?action=delete&id=<%= elem.getId()%>"><i class="fas fa-trash"></i></button>
                                                        <%} else {%>
                                                    <button type="button" disabled="true" class="btn btn-dark">CONFIRMED</button>
                                                    <%}%>
                                                </td>
                                            </tr>
                                            <%}
                                                } else {
                                                    response.sendRedirect("./StatusServlet");
                                                }%>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="row m-t-25">
                                </div>

                                <div class="chart-note">
                                    <button class="btnstatus au-btn au-btn-icon au-btn--blue">
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
        $('#modalStatus').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget)
            var id = button.data('getid')
            var date = button.data('getdate')
            var duration = button.data('getduration')
            var desc = button.data('getdescription')
            var ts = button.data('getts')
            var status = button.data('getstatus')

            var modal = $(this)
            modal.find('#SotId').val(id)
            modal.find('#Sotdate').val(date)
            modal.find('#Sotduration').val(duration)
            modal.find('#Sotdesc').val(desc)
            modal.find('#Stimesheet').val(ts)
            modal.find('#Sotstatus').val(status)
        })

        $('.btnstatus').click(function () {
            var printme = document.getElementById('statusTable');
            var wme = window.open("", "", "width=900,height=700");
            wme.document.write(printme.outerHTML);
            wme.document.close();
            wme.focus();
            wme.print();
            wme.close();
        })
    </script>

</html>
