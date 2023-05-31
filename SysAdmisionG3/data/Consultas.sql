INSERT into area_periodo(id_area, id_periodo) VALUES(1,1);

select ap.*, p.nombre as nombrep, a.nombrearea
from area_periodo ap, periodo p, areas a
where ap.id_periodo=p.id_periodo and ap.id_area=a.id_area;
