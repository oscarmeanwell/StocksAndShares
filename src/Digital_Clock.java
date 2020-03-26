import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.beans.*;
import java.io.Serializable;

public class Digital_Clock extends javax.swing.JPanel implements Runnable, Serializable {

    int hour, second, min;
    int day, month, year;
    String timestr, yearstr;
    private String timeZone = "GMT";
    
    public String getZone(){
        return timeZone;
    }
    
    public void setZone(String z){
        timeZone = z;
    }
    
    public void start(){
        Thread t = new Thread(this);
        t.start();
        initComponents();
    }
    
    public Digital_Clock() {
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        timeZoneLbl = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        dateL = new javax.swing.JLabel();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //setResizable(false);
       // getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 204), 3));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(null);
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        timeZoneLbl.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        timeZoneLbl.setForeground(new java.awt.Color(255, 51, 102));
        timeZoneLbl.setText("GMT Time");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(timeZoneLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(timeZoneLbl))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(null);
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        time.setBackground(new java.awt.Color(0, 0, 0));
        time.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("jLabel2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dateL.setBackground(new java.awt.Color(0, 0, 0));
        dateL.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        dateL.setForeground(new java.awt.Color(255, 255, 255));
        dateL.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dateL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateL)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        //getRootPane().add(jPanel1);//, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 110));
        
        //pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MethodTimezone(){
        if(!timeZoneLbl.getText().toString().equals("GMT Time")){
             setZone("GMT");
             timeZoneLbl.setText("GMT Time");      
        }
        else{
            setZone("EST");
            timeZoneLbl.setText("EST Time");
        }
    }
    
    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

        MethodTimezone();
    }//GEN-LAST:event_jPanel1MouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Digital_Clock x = new Digital_Clock();
                x.start();
                x.setVisible(true);
                //new Digital_Clock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateL;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel time;
    private javax.swing.JLabel timeZoneLbl;
    // End of variables declaration//GEN-END:variables

      
      
    @Override
    public void run() {
       
        while(true){
            try{
                //System.out.println(timeZone);
                Calendar c = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
                hour = c.get(Calendar.HOUR_OF_DAY);
                min = c.get(Calendar.MINUTE);
                second = c.get(Calendar.SECOND);
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
                SimpleDateFormat df = new SimpleDateFormat("MM:dd:yyyy");
                df.setTimeZone(TimeZone.getTimeZone(timeZone));
                Date dat = c.getTime();
                
                timestr = sdf.format(dat);
                yearstr = df.format( dat );
                time.setText(timestr);
                dateL.setText(yearstr);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}