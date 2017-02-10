<!DOCTYPE html>
<html>
<head>
	<title>FacebookApp - Home</title>
	<link rel="stylesheet" href="resources/css/style.css" type="text/css">
</head>
<body>
	<div id="loadingDataModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<h2 id="modalHeader">Loading Data...</h2>
			</div>
		</div>
	</div>
	<div id="filterModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" onclick="closeModal('filterModal');return false;">&times;</span>
				<h2 id="modalHeader">Filter By</h2>
			</div>
			<div class="modal-body">
				<button class="myButton" onclick="onFilterBy(0);return false;">Recent events</button>
				<button class="myButton" onclick="onFilterBy(1);return false;">Content</button>
				<button class="myButton" onclick="onFilterBy(2);return false;">Posts with pictures</button>
			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
	<div id="sortModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" onclick="closeModal('sortModal');return false;">&times;</span>
				<h2 id="modalHeader">Sort By</h2>
			</div>
			<div class="modal-body">
				<button class="myButton" onclick="onSortBy(0);return false;">Most liked</button>
				<button class="myButton" onclick="onSortBy(1);return false;">Most commented</button>
				<button class="myButton" onclick="onSortBy(2);return false;">Date posted</button>
			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
	<div id="content" class="table">
		<h1>Important Events</h1>
		<table id="myTable" style="width:100%">
			<thead>
				<tr>
					<td>
						<button class="myButton" onclick="onFilter();return false;">Filter</button>
						<button class="myButton" onclick="onSort();return false;">Sort</button>
					</td>
				</tr>
			</thead>
		</table>
	</div>
	<script src="resources/js/home.js"></script>
</body>
</html>