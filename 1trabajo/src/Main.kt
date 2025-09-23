
fun main() {
    encendercafetera()
    echarcafe("expresso", 1, 10)
    extraercafe(1)
    apagarcafetera()
}

fun encendercafetera(){
    println("Encendiendo la cafetera")
}
fun apagarcafetera(){
    println("Apagando la cafetera")
}

fun pocharcafe(cafe : String){
    println(" Mensaje de deporuacion: Pochando el cafe el $cafe")

}
fun echarvaso(vaso : Int){
    println("Echar el vaso $vaso")
}
fun echarcafe(cafe : String, vaso : Int, azucarina :Int){

    seleccionarazucar(azucarina)

        pocharcafe(cafe)
        echarvaso(vaso)
        echarcafe(cafe, vaso,azucarina)
    }


fun seleccionarazucar( azucarina: Int = 5){
    println("Selecionando azucarina")
}
fun extraercafe(puerta: Int){
    println("Mensaje de deporte: Extraendo el cafe de la puerta $puerta")
}
