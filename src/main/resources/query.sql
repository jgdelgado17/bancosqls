--Query para generar el reporte
SELECT 
	mv.fecha "Fecha", 
	cl.nombre "Cliente",
	ct.numero_cuenta "Numero Cuenta", 
	ct.tipo_cuenta "Tipo", 
	mv.saldo_inicial "Saldo Inicial", 
	ct.estado "Estado", 
	mv.valor "Movimiento", 
	ct.saldo "Saldo Disponible"
from cliente cl, cuenta ct, movimiento mv
WHERE
	cl.id = ct.cliente_id AND
	ct.id = mv.cuenta_id AND
	cl.id = 2 AND
	mv.fecha between '2023-05-01' AND '2023-05-03';
	
SELECT 
	mv.fecha "Fecha", 
	cl.nombre "Cliente",
	ct.numero_cuenta "Numero Cuenta", 
	ct.tipo_cuenta "Tipo", 
	mv.saldo_inicial "Saldo Inicial", 
	ct.estado "Estado", 
	mv.valor "Movimiento", 
	ct.saldo "Saldo Disponible"
FROM cliente cl
join cuenta ct
	on cl.id = ct.cliente_id
join movimiento mv
	on ct.id = mv.cuenta_id
WHERE cl.id = 2 AND
	mv.fecha between '2023-05-01' AND '2023-05-03';