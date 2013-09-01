package com.cocacola.climateambassador.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.Button;

import com.cocacola.climateambassador.models.Document;

/**
 * Created by Vinnie on 8/31/13.
 */
public class CustomDocButton extends Button {

    Document mDocument;

    public CustomDocButton(Context context, Document document) {
        super(context);
        this.mDocument = document;
    }

    public CustomDocButton(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        canvas.drawB
    }

    private int getResourceAccordingToDocType() {
        //TODO: Return appropriate Resource
        if("ppt".equals(getFileType(mDocument.getFileName()))) {

        } else if("pdf".equals(getFileType(mDocument.getFileName()))) {

        } else if("doc".equals(getFileType(mDocument.getFileName()))) {

        } else {
            return 0;
            //Do nothing
        }

        return 0;
    }

    private String getFileType(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }

        return extension;
    }

}
