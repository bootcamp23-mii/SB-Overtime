<%-- 
    Document   : approvalManager
    Created on : 18-Mar-2019, 08:19:05
    Author     : Pandu
--%>

<%@page import="java.util.List"%>
<%@page import="models.Overtime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%boolean cekData = session.getAttribute("all") != null;
        Overtime ov = (cekData) ? (Overtime) session.getAttribute("overtime") : null;
        boolean cekList = session.getAttribute("all") != null;
        if (!cekList) {
            response.sendRedirect("./HistoryServlet");
        }
    %>

    <form action="ApprovalServlet" method="POST">
        <div class="modal fade" id="modalApproval" tabindex="-1" 
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
                            <input type="text" id="AotId" name="AotId" class="form-control" value=<%= (session.getAttribute("otId") != null) ? session.getAttribute("otId") : ""%>>
                        </div>
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">Date</label>
                            <input type="date" id="Aotdate" name="Aotdate" class="form-control" value=<%= (session.getAttribute("otDate") != null) ? session.getAttribute("otDate") : ""%>>
                        </div>
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">Duration</label>
                            <input type="text" id="Aotduration" name="Aotduration" class="form-control" value=<%= (session.getAttribute("otDuration") != null) ? session.getAttribute("otDuration") : ""%>>
                        </div>
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">Description</label>
                            <input type="text" id="Aotdesc" name="Aotdesc" class="form-control" value=<%= (session.getAttribute("otDesc") != null) ? session.getAttribute("otDesc") : ""%>>
                        </div>
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">Time Sheet</label>
                            <input type="text" id="Atimesheet" name="Atimesheet" class="form-control" value=<%= (session.getAttribute("timesheet") != null) ? session.getAttribute("timesheet") : ""%>>
                        </div>
                        <div class="md-form mb-2">
                            <label data-error="wrong" data-success="true" class="">Status</label>
                            <input type="text" id="Aotstatus" class="form-control" value="STA02">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" type="submit" value="Save" name="save" >ACCEPT OVERTIME</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <div class="section__content section__content--p30">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="overview-wrap">

                        <div class="row m-t-45">
                        </div>
                        <!--<h2 class="title-1">ALL HISTORY</h2>-->
                    </div>
                </div>
            </div>
            <div class="row m-t-25">
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="au-card recent-report">
                        <div class="au-card-inner">

                            <div class="col-lg-12">
                                <div class="table-responsive table--no-card m-b-30">
                                    <!--TABLE HERE-->

                                    <table id="approvalTable" class="table table-borderless table-striped table-earning" cellspacing='30' align ='center'>
                                        <thead>
                                            <tr>
                                                <th>No.</th>
                                                <!--<th>Id</th>-->
                                                <th>Date</th>
                                                <th>Duration</th>
                                                <!--<th>Keterangan</th>-->
                                                <!--<th>Time Sheet</th>-->
                                                <!--<th>Status</th>-->
                                                <th>Aksi</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% int i = 1;
                                                if (cekList) {
                                                    for (Overtime elem : (List<Overtime>) session.getAttribute("all")) {%>
                                            <tr>
                                                <td><%= i++%></td>
                                                <!--<td><--%= // elem.getId()%></td>-->
                                                <td><%= elem.getDate()%></td>
                                                <td><%= elem.getTimeduration()%></td>
                                                <!--<td><--%= // elem.getKeterangan()%></td>-->
                                                <!--<td><--%= // elem.getTimesheet().getId()%></td>-->
                                                <!--<td><--%= // elem.getStatus().getStatus()%></td>-->
                                                <td>
                                                    <% if (elem.getStatus().getId().equals("STA01")) {%>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalApproval" 
                                                            data-getid="<%= elem.getId()%>" 
                                                            data-getdate="<%= elem.getDate()%>" 
                                                            data-getduration="<%= elem.getTimeduration()%>" 
                                                            data-getdescription="<%= elem.getKeterangan()%>" 
                                                            data-getts="<%= elem.getTimesheet().getId()%>"
                                                            data-getstatus="<%= elem.getStatus().getId()%>" 
                                                            ><i class="fas fa-check-circle"></i></button>
                                                    <button type="button" class="btn btn-danger" href="HistoryServlet?action=delete&id=<%= elem.getId()%>"><i class="fas fa-trash"></i></button>
                                                        <%} else {%>
                                                    <button type="button" disabled="true" class="btn btn-dark">CONFIRMED</button>
                                                    <%}%>
                                                </td>
                                            </tr>
                                            <%}
                                                } else {
                                                    response.sendRedirect("./HistoryServlet");
                                                }%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $('#modalApproval').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget)
            var id = button.data('getid')
            var date = button.data('getdate')
            var duration = button.data('getduration')
            var desc = button.data('getdescription')
            var ts = button.data('getts')
            var status = button.data('getstatus')

            var modal = $(this)
            modal.find('#AotId').val(id)
            modal.find('#Aotdate').val(date)
            modal.find('#Aotduration').val(duration)
            modal.find('#Aotdesc').val(desc)
            modal.find('#Atimesheet').val(ts)
            modal.find('#Aotstatus').val(status)
        })
    </script>

</html>
