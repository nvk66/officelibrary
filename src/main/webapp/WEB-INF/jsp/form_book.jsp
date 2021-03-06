<%@include file="all_tag_libs.jsp" %>
<style>
    .error {
        color: #ff0000;
    }

    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>
<body>
<div align="center">
    <h2><a href="/book">Books</a></h2>
    <h1>New/Edit Book</h1>
    <form:form action="save" method="post" modelAttribute="book">
        <form:errors path="*" cssClass="errorblock" element="div"/>
        <c:if test="${fn:length(error) > 0}">
            <div class="error">
                <c:out value="${error}"/>
            </div>
        </c:if>
        <div class="container-fluid">
            <div class="container">
                <div class="row">
                    <table>
                        <form:hidden path="id"/>
                        <tr>
                            <td>BookName:</td>
                            <td>
                                <form:input path="name"/>
                                <form:errors path="name" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Genres:</td>
                            <td>
                                <form:select path="genreIds" items="${genreList}" multiple="true" itemValue="id"
                                             itemLabel="name">
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Authors:</td>
                            <td>
                                <form:select path="authorIds" items="${authorList}" multiple="true" itemLabel="lastName"
                                             itemValue="id">
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td>PublishingYear:</td>
                            <td>
                                <form:input path="publishingYear"/>
                                <form:errors path="publishingYear" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Save" class="btn btn-primary">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </form:form>
</div>

