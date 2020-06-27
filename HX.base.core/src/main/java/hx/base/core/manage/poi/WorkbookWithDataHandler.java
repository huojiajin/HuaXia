package hx.base.core.manage.poi;

import org.apache.poi.ss.usermodel.Workbook;

public interface WorkbookWithDataHandler<E> {

    public void fillWorkbook(Workbook workbook, E data) throws Exception;
}
