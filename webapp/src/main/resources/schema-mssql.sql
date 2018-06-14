IF NOT EXISTS ( SELECT  *
                FROM    sys.schemas
                WHERE   name = N'es' ) 
    EXEC('CREATE SCHEMA [es] AUTHORIZATION [dbo]');

IF NOT EXISTS ( SELECT  *
                FROM    sys.schemas
                WHERE   name = N'mast' ) 
    EXEC('CREATE SCHEMA [mast] AUTHORIZATION [dbo]');
