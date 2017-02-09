var startIndex = window.location.href.indexOf("id=") + "id=".length;
var id = window.location.href.substr(startIndex);

//Loads the table content
var xhr1 = new XMLHttpRequest();
xhr1.open('GET', 'table?id=' + id, true);

xhr1.onreadystatechange = function() {
	if (this.readyState === 4 && this.status === 200) {
		console.log("Table content: " + this.responseText);
	}
}
xhr1.send(null);


//Loads information about the user
var xhr = new XMLHttpRequest();
xhr.open('GET', 'home?id=' + id, true);

xhr.onreadystatechange = function() {
	if (this.readyState === 4 && this.status === 200) {
		console.log("Information for the user: " + this.responseText);
	}
}
xhr.send(null);