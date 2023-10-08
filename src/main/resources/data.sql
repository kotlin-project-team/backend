CREATE OR REPLACE FUNCTION like_count() RETURNS TRIGGER AS $trigger_post_like_count$
DECLARE
    count BIGINT;
BEGIN
    IF(TG_OP = 'INSERT') THEN
        SELECT COUNT(*) INTO count FROM post_like WHERE post_id = new.post_id AND is_deleted = false;
        UPDATE post SET likes = count WHERE id = new.post_id AND is_deleted = false;
        RETURN new;
    ELSIF(TG_OP = 'UPDATE') THEN
        SELECT COUNT(*) INTO count FROM post_like WHERE post_id = old.post_id AND is_deleted = false;
        UPDATE post SET likes = count WHERE id = old.post_id AND is_deleted = false;
        RETURN old;
    END if;
    RETURN NULL;
END;
$trigger_post_like_count$ LANGUAGE plpgsql;;

DROP TRIGGER IF EXISTS trigger_post_like_count ON post_like;

CREATE TRIGGER trigger_post_like_count
AFTER INSERT OR UPDATE on post_like
FOR EACH ROW EXECUTE PROCEDURE  like_count();