<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Locations</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <h1 id="logo">HERO</h1>

            <div class="row">
                <div class="col-md-12">
                    <ul id="navigation">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/viewSightings?viewType=table">Sightings</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/viewHeroes">Heroes</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/viewVillains">Villains</a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/viewOrganizations">Organizations</a>
                        </li>
                        <li role="presentation" class="active">
                            <a href="${pageContext.request.contextPath}/viewLocations">Locations</a>
                        </li>
                    </ul>
                    <hr>
                </div>

            </div>
            <div class="row"> 
                <div class="col-md-5"  > 
                    <span class="font-size: 14px; padding: 10px 0; font-weight: bold;">Locations Archive</span>
                    <div class="scrollable">

                        <table class="table text-center displayTable" >
                            <thead>
                                <tr>
                                    <th> Name </th>
                                    <th> Description </th>
                                    <th> City </th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="i" begin="1" end="${locations.size() -1 }">
                                    <tr class='clickable-row' data-href="${pageContext.request.contextPath}/viewOrganization?viewType=view&organizationID=${oranizations[i].organizationId}">
                                        <td>
                                            <c:out value="${locations[i].locationName}"></c:out>
                                            </td>

                                            <td>
                                            <c:out value="${locations[i].description}"></c:out>
                                            </td>

                                            <td>
                                            <c:out value="${locations[i].city }" ></c:out>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/viewOranization?id=${oranizations[i].organizationId}">edit |</a>
                                            <a href="${pageContext.request.contextPath}/deleteOrganization?id=${oranizations[i].organizationId}">delete</a>
                                        </td>

                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>


                <div class="col-md-7" id="locationTab">

                    <h5>Create a new location</h5>

                    <div class="col-md-12"  id="locationMap">
                        <div></div>
                    </div>
                    <div class="col-md-12">
                        <h3></h3>
                        <p>Pro Tip: You could fill in the details manually, or you could use the interactive map
                            to select the address.</p>
                    </div>


                    <div class="col-md-12">
                        <form action="newLocation" class="displayForm" method="POST">

                            <div class="col-md-6">

                                <label for="name">Name: </label> 
                                <input class="formInput" type="text" id="name" name ="locationNameJSP" placeholder="Example (Garden of Eden)" />

                                <label for="description">Description: </label> 
                                <input class="formInput" type="text" id="description" name = "descriptionNameJSP" placeholder="Example (East New York)" />

                                <label for="latititude">Latitude: </label>
                                <br>
                                <input class="formInput" type="double" id="latitude" name = "latitudeJSP" placeholder="Example (40.34564)" />

                                <label for="longitude">Longitude: </label>
                                <br>
                                <input class="formInput" type="double" id="longitude" name = "longitudeJSP" placeholder="Example (-74.34564)" />

                                <label for="zip">Zip </label>
                                <br>
                                <input class="formInput" type="text" id="zip" name = "zipJSP" placeholder="Example (11101)" />

                            </div>

                            <div class="col-md-6">

                                <label for="streetNumber">Street Number: </label>
                                <br>
                                <input class="formInput" type="number" id="streetNumber" name = "streetNumberJSP" placeholder="Example (2719)" />

                                <label for="streetName">Street Name </label>
                                <br>
                                <input class="formInput" type="text" id="streetName" name = "streetNameJSP" placeholder="Example (Columbus Circle)" />

                                <label for="city">City </label>
                                <br>
                                <input class="formInput" type="text" id="city" name = "cityJSP" placeholder="Example (New York)" />

                                <label for="state">State </label>
                                <br>
                                <input class="formInput" type="text" id="state" name = "stateJSP" placeholder="Example (NY)" />




                                <!--                            <label for="streetNumber">Street Number </label> 
                                                            <input class="formInput" type="text" id="streetNumber" placeholder="Example (193)" />
                                
                                                            <label for="streetName">Street Name </label>
                                                            <br>
                                                            <input class="formInput" type="text" id="streetName" placeholder="Example (East 14th Street)" />
                                
                                                            <label for="city">City </label>
                                                            <br>
                                                            <input class="formInput" type="text" id="city" placeholder="Example (Brooklyn)" />
                                
                                                            <label for="state">State </label>
                                                            <br>
                                                            <input class="formInput" type="text" id="state" placeholder="Example (New York)" />-->

                            </div>


                            <button class="btn btn-primary"> Submit Location </button>
                        </form>
                    </div>


                </div>
                <br><br>

            </div>
        </div>


        <footer>
            <a>Developers</a>
            Copyright 2018 &copy;
        </footer>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/map.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDYul2fBF6a03a9OqShrMK6SVzJdfAm-NY&callback=initMap" async
        defer></script>

    </body>

</html>