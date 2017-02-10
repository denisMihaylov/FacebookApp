var startIndex = window.location.href.indexOf("id=") + "id=".length;
var userId = window.location.href.substr(startIndex);
var tableItems;
var filterVal;
var user;

var noFilter = function(item) {
	return true;
}

var filterFunction = noFilter;

// Loads the table content
var xhr1 = new XMLHttpRequest();
xhr1.open('GET', 'table?id=' + userId, true);

xhr1.onreadystatechange = function() {
	if (this.readyState === 4 && this.status === 200) {
		tableItems = JSON.parse(this.responseText);
		createTable();
	}
}
xhr1.send(null);

// Loads information about the user
var xhr = new XMLHttpRequest();
xhr.open('GET', 'home?id=' + userId, true);

xhr.onreadystatechange = function() {
	if (this.readyState === 4 && this.status === 200) {
		user = JSON.parse(this.responseText);
	}
}
xhr.send(null);

function createTable() {
	var table = document.getElementById('myTable');
	var currentTableBody = document.getElementById('myTableBody');
	var tableBody = document.createElement('tbody');
	for (var i = 0; i < tableItems.length; i++) {
		if (filterFunction(tableItems[i])) {
			var tr = document.createElement('tr');
			var td = document.createElement('td');
			if (tableItems[i].status == 'NEW') {
				td.className += ' active';
			}
			td.appendChild(document.createTextNode(tableItems[i].content));
			tr.appendChild(td);
			tableBody.appendChild(tr);
		}
	}
	if (currentTableBody) {
		table.removeChild(currentTableBody);
	}
	tableBody.setAttribute("id", "myTableBody");
	table.appendChild(tableBody);
	for (var i = 0; i < tableBody.rows.length; i++) {
		for (var j = 0; j < tableBody.rows[i].cells.length; j++)
			tableBody.rows[i].onclick = function() {
				this.cells[0].className = this.cells[0].className.replace(' active', '');
				tableItems[this.rowIndex - 1].status = 'VIEWED';
				window.open("https://www.facebook.com/" + tableItems[this.rowIndex - 1].id, '_blank');
				var xhr = new XMLHttpRequest();
				xhr.open('POST', 'view', true);
				xhr.send(JSON.stringify({
					feedId : tableItems[this.rowIndex - 1].id,
					userId : userId
				}));
			};
	}
}

function onFilter() {
	document.getElementById('filterModal').style.display = "block";
}

function onSort() {
	document.getElementById('sortModal').style.display = "block";
}

function onSetInterest() {
	document.getElementById('setInterestModal').style.display = "block";
}

function closeModal(modalId) {
	document.getElementById(modalId).style.display = "none";
}

var sortFunctions = [function(a, b) {
	return b.likesCount - a.likesCount;
}, function(a, b) {
	return b.commentsCount - a.commentsCount;
}, function(a, b) {
	return new Date(b.postDate).getTime() - new Date(a.postDate).getTime();
}];

function onSortBy(sortType) {
	tableItems.sort(sortFunctions[sortType]);
	createTable();
	closeModal('sortModal');
}

var filterFunctions = [function(item) {
	return new Date(item.postDate).getTime() + filterVal * 24 * 60 * 60 * 1000 > new Date().getTime();
}, function(item) {
	return item.content.indexOf(filterVal) != -1;
}, function(item) {
	return item.pictureUrl;
}];

var beforeFilter = [function() {
	do {
		filterVal= prompt("Please enter the maximum age of a post in days", "1");
	} while (parseInt(filterVal, 10) != filterVal);
}, function() {
	filterVal = prompt("Please enter a search string", user.firstName + " " + user.lastName);
}, function() {
	
}];

function onFilterBy(filterType) {
	beforeFilter[filterType]();
	filterFunction = filterFunctions[filterType];
	createTable();
	closeModal('filterModal');
}