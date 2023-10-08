CREATE OR REPLACE FUNCTION like_count() RETURNS TRIGGER AS $trigger_post_like_count$
DECLARE
    count bigint;
BEGIN
    if(TG_OP = 'INSERT') then
        select count(*) into count from post_like where post_id = new.post_id AND is_deleted = false;
        update post set likes = count where id = new.post_id AND is_deleted = false;
        raise notice 'this is %',count;
        raise notice 'this is %',new.post_id;
        return new;
    elsif(TG_OP = 'UPDATE') then
        select count(*) into count from post_like where post_id = old.post_id AND is_deleted = false;
        update post set likes = count where id = old.post_id AND is_deleted = false;
        raise notice 'this is %',count;
        raise notice 'this is %',old.post_id;
        return old;
    end if;
    return null;
END;
$trigger_post_like_count$ language plpgsql;;

DROP TRIGGER IF EXISTS trigger_post_like_count ON post;

create trigger trigger_post_like_count
after insert or update on post_like
for each row execute procedure like_count();
