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

	(vaio-laptop(series f)(model VPCF11X5E)(use multimedia gamer design)(weight 2.4)
	(cpuname corei7)(cpusp 2.80)(ramsize 4)(ramsp 1333)(hdsize 320)(media br)(screensize 16.4)
	(graphicsname 330m)(dedicated 1024)(shared 0)(nusb 2)(optical 1)(battime 180)(price 1159))

	(vaio-laptop(series f)(model VPCF11V5E)(use multimedia gamer design)(weight 2.4)
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
	(cpuname atomn450)(cpusp 1.66)(ramsize 1)(ramsp 667)(ramtype 2)(hdsize 160)
	(media no)(screensize 10.1)(graphicsname gma950)(dedicated 0)(shared 250)
	(eth 100)(nusb 2)(hdmi 0)(battime 450)(price 374))

	(vaio-laptop(series z)(model VPCZ11V5E)(so w7pro64)(use office design gamer)
	(weight 1.55)(cpuname corei5)(cpusp 2.40)(ramsize 4)(ramsp 1066)(hdsize 500)
	(media no)(screensize 13.1)(graphicsname 330m)(dedicated 1024)(shared 0)
	(wifi a b g n)(battime 270)(price 1470))

	(vaio-laptop(series z)(model VPCZ11X5E)(so w7pro64)(use office design gamer)
	(weight 1.55)(cpuname corei5)(cpusp 2.40)(ramsize 4)(ramsp 1066)(hdsize 128)
	(hdtype ssd)(screensize 13.1)(graphicsname 330m)(dedicated 1024)(shared 0)
	(wifi a b g n)(battime 270)(price 1800))

	(vaio-laptop(series x)(model VPCX11X5E)(so w7pro32)(use mini office)(weight 0.78)
	(cpuname atomz540)(cpusp 1.86)(ramsize 2)(ramsp 533)(ramtype 2)(hdsize 64)
	(hdtype ssd)(media no)(screensize 11.1)(graphicsname gma500)(dedicated 0)(shared 760)
	(nusb 2)(hdmi 0)(wan 1)(battime 240)(price 1469))
) 

; ********
; DEFFUNCTIONS
; ********

;(deffunction ask-start-again ()
;  (printout t "¿Desea otra consulta? (y/n) ")
;  (if (eq (read) y) then
;    (assert (phase choose-query))))

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
		   "Introduzca el numero de la consulta: ")
   (assert (query-select (read))))

(defrule bad-player-choice 
   ?phase <- (phase choose-query)
   ?choice <- (query-select ?query&~1&~2&~3&~4)
   =>
   (retract ?phase ?choice)
   (printout t "Elija un numero del 1 al 4." crlf)
   (assert (phase choose-query)))

(defrule good-query-choice
   ?phase <- (phase choose-query)
   ?choice <- (query-select ?query&:(or (eq ?query 1)(eq ?query 2)(eq ?query 3)(eq ?query 4)))
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
	?p <- (vaio-laptop {price <= ?pri})
	=>
	(printout t "Modelo: " ?p.model crlf))

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
	?p <- (vaio-laptop {ramsize >= ?r})
	=>
	(printout t "Modelo: " ?p.model crlf))

(defrule exec-query-3
	?phase <- (phase exec-query)
	?query <- (query-select 3)
	?p <- (vaio-laptop (use $? gamer $?))
	=>
	(printout t "Modelo: "?p.model crlf))

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
	(assert (phase exec-tri))
	)
	
(defrule exec-tri
	(weight ?w)
	(screen ?s)
	(price ?pri)
	(phase exec-tri)
	?p <- (vaio-laptop {screensize <= ?s && price <= ?pri && weight <= ?w})
	=>
	(printout t "Modelo: " ?p.model crlf))

(reset)
(run)

