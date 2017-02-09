var startIndex = window.location.href.indexOf("id=") + "id=".length;
var id = window.location.href.substr(startIndex);
var tableItems;

// Loads the table content
var xhr1 = new XMLHttpRequest();
xhr1.open('GET', 'table?id=' + id, true);

xhr1.onreadystatechange = function() {
	if (this.readyState === 4 && this.status === 200) {
		tableItems = JSON.parse(this.responseText);
		createTable();
	}
}
xhr1.send(null);

// Loads information about the user
var xhr = new XMLHttpRequest();
xhr.open('GET', 'home?id=' + id, true);

xhr.onreadystatechange = function() {
	if (this.readyState === 4 && this.status === 200) {
		console.log("Information for the user: " + this.responseText);
	}
}
xhr.send(null);

function createTable() {
	var content = document.getElementById("content");
	var table = document.createElement('table');
	table.setAttribute("id", "myTable");
	table.setAttribute('border', '1');
	var tableBody = document.createElement('tbody');
	for (var i = 0; i < tableItems.length; i++) {
		var tr = document.createElement('tr');
		var td = document.createElement('td');
		if (tableItems[i].status == 'NEW') {
			td.className += ' active';
		}
		td.appendChild(document.createTextNode(tableItems[i].content));
		tr.appendChild(td);
		tableBody.appendChild(tr);
	}
	table.appendChild(tableBody);
	content.appendChild(table);
	if (table != null) {
		for (var i = 0; i < table.rows.length; i++) {
			for (var j = 0; j < table.rows[i].cells.length; j++)
				table.rows[i].onclick = function() {
					console.log(this.rowIndex);
					window.open("https://www.facebook.com/" + tableItems[this.rowIndex].id, '_blank');
					var xhr = new XMLHttpRequest();
					xhr.open('POST', 'view', true);
					var rowIndex = this.rowIndex;
					xhr.onreadystatechange = function() {
						if (this.readyState === 4 && this.status === 200) {
							console.log("Item has been read");
						}
					}
					xhr.send(JSON.stringify({
						id : tableItems[this.rowIndex].id
					}));
				};
		}
	}
}