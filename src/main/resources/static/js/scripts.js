function actorSelected(select){
	const index = select.selectedIndex;
	const option = select.options[index];
	
	const id = option.value;
	const name = option.text;
	const imageUrl = option.dataset.url;
	
	option.disabled = "disabled";
	select.selectedIndex = 0;
	
	addActor(id, name, imageUrl);
	
	const ids = $("#ids").val();
	
	if(ids === ""){
		$("#ids").val(id);
	} else {
		$("#ids").val(ids + "," + id);
	}
}

function addActor(id, name, imageUrl){
	let htmlString =	`
	<div class="card col-md-3 m-2" style="width: 10rm">
		<img src="{IMAGE_URL}" class="card-img-top">
		<div class="card-body">
			<p class="card-text">{NAME}</p>
			<button class="btn btn-danger" data-id="{ID}" onclick="deleteActor(this); return false;">Delete</button>
		</div>
	</div>`;
	
	htmlString = htmlString.replace("{IMAGE_URL}", imageUrl);
	htmlString = htmlString.replace("{NAME}", name);
	htmlString = htmlString.replace("{ID}", id);
	
	$("#actors_container").append(htmlString);
}

function deleteActor(btn){
	const id = btn.dataset.id;
	const node = btn.parentElement.parentNode;
	const idArray = $("#ids").val().split(",").filter(actorId => actorId !== id);
	
	$("#ids").val(idArray.join(","));
	
	$("#protagonists option[value='"+ id +"']").prop("disabled", false);
	
	$(node).remove();
}


function previsualization() {
	let reader = new FileReader();
	const image = document.getElementById('file').files[0];
	const preview = document.getElementById('preview');
	
	reader.readAsDataURL(image);
	
	reader.onload = function(e){
		preview.classList.remove("d-none");
		preview.style.backgroundImage = 'url("'+ e.target.result +'")';
	}
}









