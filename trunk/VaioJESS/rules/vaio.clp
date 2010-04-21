(deftemplate vaio-data "VAIO laptop info"
	(slot series) ; the series of the laptop
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
	(slot optical (type STRING) (default dvd)) 	; optical media (dvd, br, no)
	(slot screentype (type STRING) (default led))
	(slot screensize (type FLOAT)) 			; size of the screen in ''
	(slot graphicsname (type STRING)) 			; name of the graphics card model
	(slot dedicated (type INTEGER)) 			; size of the dedicated graphic memory in MB
	(slot shared (type INTEGER)) 				; size of the shared graphic memory in MB
	(multislot wifi) 						; a mixture of the following: a, b, g, n
	(slot eth (type INTEGER) (default 1000))
	(slot nusb (type INTEGER) (default 3))
	(slot hdmi (default 1))
	(slot optical (default 0))
	(slot battime (type INTEGER)) 			; duration of the battery in minutes
	(slot batrech (type INTEGER)) 			; time to recharge the battery
	(slot dataurl (type STRING)) 				
	(slot buyurl (type STRING))
	(slot price (type INTEGER))				; laptops price in euros
)