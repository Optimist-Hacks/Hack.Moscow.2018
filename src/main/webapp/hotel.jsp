<%@ page import="com.hackdocs.model.businessModels.Document" %>
<%@ page import="com.hackdocs.model.businessModels.FieldType" %>
<%@ page import="com.hackdocs.service.logic.HotelLogic" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>


<body style="margin:50px">

<%
    if (HotelLogic.COMPLETED_DOCUMENTS.isEmpty()) {
%>

<h1>No registration yet =(</h1>
<p align="center" style="margin-left: auto;margin-right: auto;">
    <iframe src="https://giphy.com/embed/3d2wgrPNcBqocFzFbJ" width="960" height="540" frameBorder="0"
            class="giphy-embed" allowFullScreen></iframe>
</p>
<%
} else {
%>

<h1>Registered people</h1>

<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First name</th>
        <th scope="col">Last name</th>
        <th scope="col">Country</th>
        <th scope="col">City</th>
        <th scope="col">Cell phone</th>
        <th scope="col">Email</th>
        <th scope="col">Departure date</th>
        <th scope="col">Departure time</th>
        <th scope="col">Pdf</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (int i = 0; i < HotelLogic.COMPLETED_DOCUMENTS.size(); i++) {
            Document document = HotelLogic.COMPLETED_DOCUMENTS.get(i);

    %>
    <tr>
        <th scope="row"><%=i%>
        </th>
        <td><%=document.getFieldByType(FieldType.NAME)%>
        </td>
        <td><%=document.getFieldByType(FieldType.LASTNAME)%>
        </td>
        <td><%=document.getFieldByType(FieldType.COUNTRY)%>
        </td>
        <td><%=document.getFieldByType(FieldType.CITY)%>
        </td>
        <td><%=document.getFieldByType(FieldType.CELL_PHONE)%>
        </td>
        <td><%=document.getFieldByType(FieldType.EMAIL)%>
        </td>
        <td><%=document.getFieldByType(FieldType.DEPARTURE_DATE)%>
        </td>
        <td><%=document.getFieldByType(FieldType.DEPARTURE_TIME)%>
        </td>
        <td>
            <link rel="open file" href="<%=document.getFieldByType(FieldType.DEPARTURE_TIME)%>">
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
    }
%>
</body>
</html>