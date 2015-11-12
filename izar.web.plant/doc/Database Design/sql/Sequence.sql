-- Sequence: hys_seq_storage_id

-- DROP SEQUENCE hys_seq_storage_id;

CREATE SEQUENCE hys_seq_storage_id
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999999
  START 100001
  CACHE 1;
ALTER TABLE hys_seq_storage_id
  OWNER TO postgres;