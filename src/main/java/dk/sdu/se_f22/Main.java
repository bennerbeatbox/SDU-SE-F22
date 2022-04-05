package dk.sdu.se_f22;

import dk.sdu.se_f22.brandmodule.management.persistence.Persistence;
import dk.sdu.se_f22.brandmodule.management.services.IndexingService;
import dk.sdu.se_f22.sharedlibrary.db.DBMigration;
import dk.sdu.se_f22.sharedlibrary.db.LoggingProvider;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LoggingProvider.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Program startup...");

        IndexingService service = new IndexingService();
        Persistence p = new Persistence();
        p.setIndexingInterval(6900);
        service.StartIndexInterval();
    }
}
