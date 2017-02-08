var startIndex = window.location.href.indexOf("id=") + "id=".length;
var id = window.location.href.substr(startIndex);
var xhr = new XMLHttpRequest();
xhr.open('GET', '/FacebookApp/home?id=' + id, true);

xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		console.log(xhr.responseText);
	}
}
xhr.send(null);