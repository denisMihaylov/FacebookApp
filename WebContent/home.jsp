<!DOCTYPE html>
<html>
<head>
	<title>FacebookApp - Home</title>
	<link rel="stylesheet" href="resources/css/style.css" type="text/css">
</head>
<body>
	<div id="filterModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" onclick="closeModal('filterModal');return false;">&times;</span>
				<h2 id="modalHeader">Filter By</h2>
			</div>
			<div class="modal-body">
				<p>Some text in the Modal Body</p>
				<p>Some other text...</p>
			</div>
			<div class="modal-footer">
				<h3>Modal Footer</h3>
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
	<div id="setInterestModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span class="close" onclick="closeModal('setInterestModal');return false;">&times;</span>
				<h2 id="modalHeader">Manage Interests</h2>
			</div>
			<div class="modal-body">
				<p>Some text in the Modal Body</p>
				<p>Some other text...</p>
			</div>
			<div class="modal-footer">
				<h3>Modal Footer</h3>
			</div>
		</div>
	</div>
	<div id="content" class="table">
		<h1>Important Events</h1>
		<table id="myTable">
			<thead>
				<tr>
					<td>
						<button class="myButton" onclick="onFilter();return false;">Filter</button>
						<button class="myButton" onclick="onSort();return false;">Sort</button>
						<button class="myButton" onclick="onSetInterest();return false;" style="width:150px">Manage Interests</button>
					</td>
				</tr>
			</thead>
		</table>
	</div>
	<script src="resources/js/home.js"></script>
</body>
</html>