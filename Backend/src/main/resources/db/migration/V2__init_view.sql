create or replace view v_events_images as
select ce.id as event_id, ci.id as img_id, ce.adult as adult, ce.covid_certificate as covid_certificate
from city_events ce
         join
     city_images ci on ce.image_id = ci.id;

-- create or replace view v_locales_reviews as
-- select cl.id as locale_id,cl.name,cl.type,cl.logoUrl,cl.numTables,cl.review,
-- from city_locales cl
--          join
--      city_reviews cr on cl.id = cr.locale_id;