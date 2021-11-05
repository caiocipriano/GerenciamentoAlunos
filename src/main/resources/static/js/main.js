function GerarMatricula(){
	var txt = "ACA";
	var aleatorio = Math.floor(Math.random() * 100);
	document.getElementById('matricula').value=(txt + aleatorio);
}