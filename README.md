## API  
  
La API con la que conecta es la del [Clash of Clans](https://developer.clashofclans.com/), para que funcione es necesario registrarse, crear un token y registrar la IP desde la que se van a hacer las peticiones.  

El token creado hay que ponerlo en el archivo /env/ApiKey_Example y renombrar el archivo a ApiKey.  
  
## Servicio  
Expone el endpoint /api/top-ranking/{locationId}/members, siendo locationId el atributo id de la clase Location de la API de Clash of Clan.  

Ha sido diseñada para ser consumida desde el repositorio [PS-AA2](https://github.com/MMAndresC/PS-AA2)  
  
Se ha configurado para que use el puerto 8081, si es necesario cambiarlo, editar el archivo /resources/application.properties