; *************************
; GRUPO 10
; Pablo Acevedo Montserrat
; Alfredo Díez Zamarro
; Jorge Guirado Alonso 
; *************************

; ********
; DEFTEMPLATE 
; ********

(deftemplate vaio-laptop "VAIO laptop info"
	(slot series) 						; the series of the laptop
	(slot model (type STRING)) 				; the model of the laptop
	(slot so (type STRING) (default w7home64)) 	; operating system
	(multislot use) 						; a mixture of the following: mini, office, multimedia, gamer, design
	(slot weight (type FLOAT)) 				; weight of the laptop. Kg
	(slot cpuman (type STRING) (default intel)) 	; manufacturer of the laptops cpu
	(slot cpuname (type STRING)) 				; cpu model name
	(slot cpusp (type FLOAT)) 				; cpu speed in Ghz
	(slot ramsize (type INTEGER)) 			; ram size in GB
	(slot ramsp (type INTEGER)) 				; ram speed in Mhz
	(slot ramtype (default 3)) 				; 2 for ddr2, 3 for ddr3
	(slot hdsize (type INTEGER)) 				; hard disks size in GB
	(slot hdtype (type STRING) (default sata)) 	; hard disks interfaces type
	(slot media (type STRING) (default dvd)) 	; optical media (dvd, br, no)
	(slot screentype (type STRING) (default led))
	(slot screensize (type FLOAT)) 			; size of the screen in ''
	(slot graphicsname (type STRING)) 			; name of the graphics card model
	(slot dedicated (type INTEGER)) 			; size of the dedicated graphic memory in MB
	(slot shared (type INTEGER)) 				; size of the shared graphic memory in MB
	(multislot wifi (default b g n))			; a mixture of the following: a, b, g, n
	(slot eth (type INTEGER) (default 1000))
	(slot nusb (type INTEGER) (default 3))
	(slot hdmi (default 1))
	(slot optical (default 0))
	(slot bluet (default 1))
	(slot wan (default 0))
	(slot battime (type INTEGER)) 			; duration of the battery in minutes
	(slot dataurl (type STRING) (default http://www.sony.es/hub/vaio)) 				
	(slot buyurl (type STRING) (default http://www.sonystyle.es))
	(slot price (type INTEGER))				; laptops price in euros
)

; ********
; DEFFACTS 
; ********

(deffacts initial-phase
   (phase choose-query))

(deffacts vaio-data "VAIO laptop database"

	(vaio-laptop(series e)(model VPCEC1X5E)(use multimedia gamer)(weight 3.3)
	(cpuname corei3)(cpusp 2.13)(ramsize 2)(ramsp 1066)(hdsize 320)(screensize 17.3)
	(graphicsname atihd5470)(dedicated 512)(shared 2231)(battime 210)(price 729))

	(vaio-laptop(series e)(model VPCEB1X5E)(use multimedia office)(weight 2.7)
	(cpuname corei5)(cpusp 2.23)(ramsize 3)(ramsp 1066)(hdsize 320)(screensize 15.5)
	(graphicsname intelhd)(dedicated 0)(shared 1755)(battime 210)(price 679))

	(vaio-laptop(series e)(model VPCEA1X5E)(use multimedia gamer)(weight 2.3)
	(cpuname corei3)(cpusp 2.13)(ramsize 3)(ramsp 1066)(hdsize 320)(screensize 14)
	(graphicsname atihd4570)(dedicated 512)(shared 2231)(battime 210)(price 699))

	(vaio-laptop(series f)(model VPCF11X5E)(use multimedia gamer)(weight 2.4)
	(cpuname corei7)(cpusp 2.80)(ramsize 4)(ramsp 1333)(hdsize 320)(media br)(screensize 16.4)
	(graphicsname 330m)(dedicated 1024)(shared 0)(nusb 2)(optical 1)(battime 180)(price 1159))

	(vaio-laptop(series f)(model VPCF11V5E)(use multimedia gamer)(weight 2.4)
	(cpuname corei3)(cpusp 2.13)(ramsize 2)(ramsp 1066)(hdsize 320)(screensize 16.4)
	(graphicsname 310m)(dedicated 512)(shared 0)(nusb 2)(optical 1)(battime 180)(price 779))

	(vaio-laptop(series y)(model VPCY11V9E)(so w7pro64)(use multimedia office)
	(weight 1.78)(cpuname c2d)(cpusp 1.30)(ramsize 4)(ramsp 800)(hdsize 320)(media no)
	(screensize 13.3)(graphicsname gma4500hd)(dedicated 0)(shared 1760)(wan 1)
	(battime 540)(price 899))	

	(vaio-laptop(series s)(model VPCS11V5E)(use multimedia office)(weight 2.13)
	(cpuname corei5)(cpusp 2.40)(ramsize 2)(ramsp 1066)(hdsize 320)(screensize 13.3)
	(graphicsname intelhd)(dedicated 0)(shared 755)(wifi a b g n)(battime 300)
	(price 889))

	(vaio-laptop(series s)(model VPCS11X5E)(use multimedia office gamer)(weight 2.13)
	(cpuname corei5)(cpusp 2.40)(ramsize 2)(ramsp 1066)(hdsize 320)(media br)
	(screensize 13.3)(graphicsname 310m)(dedicated 512)(shared 0)(wifi a b g n)(battime 300)
	(price 1569))

	(vaio-laptop(series w)(model VPCW21X5E)(so w7starter32)(use mini)(weight 1.2)
	(cpuname atom)(cpusp 1.66)(ramsize 1)(ramsp 667)(ramtype 2)(hdsize 160)
	(media no)(screensize 10.1)(graphicsname gma950)(dedicated 0)(shared 250)
	(eth 100)(nusb 2)(hdmi 0)(battime 450)(price 374))

	(vaio-laptop(series z)(model VPCZ11V5E)(so w7pro64)(use office gamer)
	(weight 1.55)(cpuname corei5)(cpusp 2.40)(ramsize 4)(ramsp 1066)(hdsize 500)
	(media no)(screensize 13.1)(graphicsname 330m)(dedicated 1024)(shared 0)
	(wifi a b g n)(battime 270)(price 1470))

	(vaio-laptop(series z)(model VPCZ11X5E)(so w7pro64)(use office gamer)
	(weight 1.55)(cpuname corei5)(cpusp 2.40)(ramsize 4)(ramsp 1066)(hdsize 128)
	(hdtype ssd)(screensize 13.1)(graphicsname 330m)(dedicated 1024)(shared 0)
	(wifi a b g n)(battime 270)(price 1800))

	(vaio-laptop(series x)(model VPCX11X5E)(so w7pro32)(use mini office)(weight 0.78)
	(cpuname atom)(cpusp 1.86)(ramsize 2)(ramsp 533)(ramtype 2)(hdsize 64)
	(hdtype ssd)(media no)(screensize 11.1)(graphicsname gma500)(dedicated 0)(shared 760)
	(nusb 2)(hdmi 0)(wan 1)(battime 240)(price 1469))
) 

; ********
; DEFFUNCTIONS
; ********

(deffunction print-model (?vaio)
  (printout t "------------------------------------------------" crlf
			  "Modelo: VAIO serie " ?vaio.series " " ?vaio.model crlf
			  "URL: " ?vaio.buyurl crlf
			  "------------------------------------------------" crlf)
  )

; *****
; RULES 
; *****

(defrule query-select
   (phase choose-query)
   =>
   (printout t "Seleccione la consulta a realizar:" crlf
           "1. Por precio." crlf
		   "2. Por memoria RAM." crlf
		   "3. Uso para juegos." crlf
		   "4. Combinada: peso, pantalla, precio." crlf
		   "5. Salida HDMI, audio optico y Blu-Ray." crlf
		   "6. Todas las caracteristicas." crlf
		   "Introduzca el numero de la consulta: ")
   (assert (query-select (read))))

(defrule bad-player-choice 
   ?phase <- (phase choose-query)
   ?choice <- (query-select ?query&~1&~2&~3&~4&~5&~6)
   =>
   (retract ?phase ?choice)
   (printout t "Elija un numero del 1 al 6." crlf)
   (assert (phase choose-query)))

(defrule good-query-choice
   ?phase <- (phase choose-query)
   ?choice <- (query-select ?query&:(or (eq ?query 1)(eq ?query 2)(eq ?query 3)(eq ?query 4)(eq ?query 5)(eq ?query 6)))
   =>
   (retract ?phase)
   (assert (phase exec-query)))

(defrule exec-query-1
    ?phase <- (phase exec-query)
    ?query <- (query-select 1)
    =>
	(printout t "Introduzca el precio maximo deseado: ")
    (retract ?phase ?query)
	(assert (price (read)))
	(assert (phase exec-price)))
	
(defrule exec-price
	(price ?pri)
	(phase exec-price)
	?vaio <- (vaio-laptop {price <= ?pri})
	=>
	(print-model ?vaio))

(defrule exec-query-2
	?phase <- (phase exec-query)
	?query <- (query-select 2)
	=>
	(printout t "Introduzca la cantidad de memoria RAM deseada: ")
	(retract ?phase ?query)
	(assert (ram (read)))
	(assert (phase exec-ram)))
 
(defrule exec-ram
	(ram ?r)
	(phase exec-ram)
	?vaio <- (vaio-laptop {ramsize >= ?r})
	=>
	(print-model ?vaio))

(defrule exec-query-3
	?phase <- (phase exec-query)
	?query <- (query-select 3)
	?vaio <- (vaio-laptop (use $? gamer $?))
	=>
	(print-model ?vaio))

(defrule exec-query-4
	?phase <- (phase exec-query)
	?query <- (query-select 4)
	=>
	(printout t "Introduzca el peso maximo: ")
	(assert (weight (read)))
	(printout t "Introduzca las pulgadas maximas de la pantalla: ")
	(assert (screen (read)))
	(printout t "Introduzca el precio maximo en euros: ")
	(assert (price (read)))
	(assert (phase exec-tri)))
	
(defrule exec-tri
	(weight ?w)
	(screen ?s)
	(price ?pri)
	(phase exec-tri)
	?vaio <- (vaio-laptop {screensize <= ?s && price <= ?pri && weight <= ?w})
	=>
	(print-model ?vaio))
	
(defrule exec-query-5
	?phase <- (phase exec-query)
	?query <- (query-select 5)
	?vaio <- (vaio-laptop {hdmi == 1 && bluet == 1 && media == br})
	=>
	(print-model ?vaio))
	
(defrule exec-query-6
	?phase <- (phase exec-query)
	?query <- (query-select 6)
	=>
	(printout t "RANGO DE PESO:" crlf
				"1. Mini:  <2 kg" crlf
				"2. Medio: <2.4 kg" crlf
				"3. No importa el peso" crlf)
	(printout t "Introduzca el rango: ")
	(assert (peso (read)))
	(printout t "SELECCIONE PROCESADOR:" crlf
				"1. Core i3" crlf
				"2. Core i5" crlf
				"3. Core i7" crlf
				"4. Core 2 Duo" crlf
				"5. Atom" crlf)
	(printout t "Introduzca el rango: ")
	(assert (proc (read)))
	(printout t "SELECCIONE MEMORIA RAM:" crlf
				"1. 1 Gb DDR2" crlf
				"2. 2-3 Gb DDR3" crlf
				"3. +4 Gb DDR3" crlf)
	(printout t "Introduzca el rango: ")
	(assert (memoria (read)))
	(printout t "SELECCIONE TIPO DE MEDIO:" crlf
				"1. Blu-Ray" crlf
				"2. Combo DVD" crlf
				"3. Ninguno" crlf)
	(printout t "Introduzca el rango: ")
	(assert (tmedio (read)))
	(printout t "SELECCIONE MEMORIA GRAFICA:" crlf
				"1. Dedicada 512 Mb" crlf
				"2. Dedicada 1024 Mb" crlf
				"3. Compartida" crlf)
	(printout t "Introduzca el rango: ")
	(assert (mgraf (read)))
	(printout t "SELECCIONE RANGO DE PRECIO:" crlf
				"1. Barato: <700 euros" crlf
				"2. Medio:  <1200 euros" crlf
				"3. No importa el precio" crlf)
	(printout t "Introduzca el rango: ")
	(assert (rprecio (read)))
	(retract ?phase ?query)
	(assert (phase exec-total))
	)

(defrule weight1
	?p <- (peso 1)
	=>
	(retract ?p)
	(assert (weight 0))
	(assert (weightop 2))
	)
	
(defrule weight2
	?p <- (peso 2)
	=>
	(retract ?p)
	(assert (weight 2))
	(assert (weightop 2.3))
	)
	
(defrule weight3
	?p <- (peso 3)
	=>
	(retract ?p)
	(assert (weight 2.3))
	(assert (weightop 5))
	)
	
(defrule cpu1
	?p <- (proc 1)
	=>
	(retract ?p)
	(assert (cpuname corei3))
	)
	
(defrule cpu2
	?p <- (proc 2)
	=>
	(retract ?p)
	(assert (cpuname corei5))
	)
	
(defrule cpu3
	?p <- (proc 3)
	=>
	(retract ?p)
	(assert (cpuname corei7))
	)
	
(defrule cpu4
	?p <- (proc 4)
	=>
	(retract ?p)
	(assert (cpuname c2d))
	)
	
(defrule cpu5
	?p <- (proc 5)
	=>
	(retract ?p)
	(assert (cpuname atom))
	)
	
(defrule ramsize1
	?p <- (memoria 1)
	=>
	(retract ?p)
	(assert (ramsize 0))
	(assert (ramsizetop 2))
	)
	
(defrule ramsize2
	?p <- (memoria 2)
	=>
	(retract ?p)
	(assert (ramsize 2))
	(assert (ramsizetop 4))
	)
	
(defrule ramsize3
	?p <- (memoria 3)
	=>
	(retract ?p)
	(assert (ramsize 4))
	(assert (ramsizetop 8))
	)
	
(defrule media1
	?p <- (tmedio 1)
	=>
	(retract ?p)
	(assert (media br))
	)
	
(defrule media2
	?p <- (tmedio 2)
	=>
	(retract ?p)
	(assert (media dvd))
	)
	
(defrule media3
	?p <- (tmedio 3)
	=>
	(retract ?p)
	(assert (media no))
	)
	
(defrule dedicated1
	?p <- (mgraf 1)
	=>
	(retract ?p)
	(assert (dedicated 512))
	)
	
(defrule dedicated2
	?p <- (mgraf 2)
	=>
	(retract ?p)
	(assert (dedicated 1024))
	)
	
(defrule dedicated3
	?p <- (mgraf 3)
	=>
	(retract ?p)
	(assert (dedicated 0))
	)
	
(defrule price1
	?p <- (rprecio 1)
	=>
	(retract ?p)
	(assert (price 0))
	(assert (pricetop 700))
	)
	
(defrule price2
	?p <- (rprecio 2)
	=>
	(retract ?p)
	(assert (price 700))
	(assert (pricetop 1200))
	)
	
(defrule price3
	?p <- (rprecio 3)
	=>
	(retract ?p)
	(assert (price 1200))
	(assert (pricetop 3000))
	)
		
(defrule exec-total
	(weight ?w)
	(weightop ?wt)
	(cpuname ?n)
	(ramsize ?r)
	(ramsizetop ?rt)
	(media ?m)
	(dedicated ?d)
	(price ?p)
	(pricetop ?pt)
	?phase <- (phase exec-total)
	?vaio <- (vaio-laptop {weight > ?w && weight <= ?wt && cpuname == ?n && ramsize >= ?r && ramsize < ?rt && media == ?m && dedicated == ?d && price > ?p && price <= ?pt})
	=>
	(print-model ?vaio)
	(retract ?phase)
	)	
	
(reset)
(run)

