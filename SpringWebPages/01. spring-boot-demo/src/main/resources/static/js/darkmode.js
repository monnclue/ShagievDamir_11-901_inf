 /*   <%
    String mode;
    if(Arrays.stream(request.getCookies())
    .noneMatch(cookie -> cookie.getName().equals("mode"))) {
        mode = "day";
    } else {
        mode = Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals("mode")).findFirst().get().getValue();
    }
    %>

  */

 let mode = '<%=mode%>';

 function toggleMode() {
        let body = document.getElementsByTagName("body").item(0);
        if (mode === 'night') body.classList.add('dark-mode');
        else body.classList.remove('dark-mode');
 }

 toggleMode();
