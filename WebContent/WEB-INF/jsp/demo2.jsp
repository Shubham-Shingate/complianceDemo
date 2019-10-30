<%@page import="java.util.LinkedList"%>
<%@page import="com.axis.compliance.MoreInfoDAO"%>
<%@page import="com.axis.compliance.complianceNew"%>
<%@page import="com.validate.compilanceValidatePojo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.validate.ExecuteCompliance"%>
<%@page import="java.util.Date"%>
<%@page import="com.rest.ValidResult"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.validate.ComplanceValidate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">    
      <title>Insert title here</title>
      <meta name="viewport" content="width=device-width, initial-scale=1"> 
       <link rel="stylesheet" type="text/css" href="assets/css/style.css"/>
         <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css"/> 
            <link rel="stylesheet" href="assets/css/jquery.dataTables.min.css">
      <link rel = "stylesheet" href = "assets/css/font-awesome.min.css">
      <script src="assets/js/jquery.min.js"></script>
      <script src="assets/js/bootstrap.min.js"></script>
      <script src="assets/js/jquery.dataTables.min.js"></script> 
      <script>
 $(document).ready(function(){
        		 
                 $('.statusClass').click(function ()
                 {
                	  var valuePass=this.id;
                	  //alert(valuePass);
                
				  //var valuePass = valuePass.replace("No of Original & copy", "No of Original and copy");
				    var valuePass_clean = encodeURIComponent(valuePass);

                     $.ajax({
                         type: "post",
                         url: "testServlet", //this is my servlet
                         data: "id=" +valuePass_clean,
                         success: function(msg){ 
                        	// alert(msg);
                                 $('#output').append(msg);
                         }
                     });
                 });

   
             $('#myTable').dataTable({searching: false});
             
             $('a[title]').tooltip();
          	 setTimeout(function(){
          		$('body').addClass('loaded');
          		$('h1').css('color','#222222')
          	 }, 3000);
          	
            
 });
         
         function openCity(evt, cityName) {
         	  var i, tabcontent, tablinks;
         	  tabcontent = document.getElementsByClassName("tabcontent");
         	  for (i = 0; i < tabcontent.length; i++) {
         	    tabcontent[i].style.display = "none";
         	  }
         	  tablinks = document.getElementsByClassName("tablinks");
         	  for (i = 0; i < tablinks.length; i++) {
         	    tablinks[i].className = tablinks[i].className.replace(" active", "");
         	  }
         	  document.getElementById(cityName).style.display = "block";
         	  evt.currentTarget.className += " active";
         	}
      </script>
   </head>
   <body>
      <div id="loader-wrapper">
    
         <div id="loader"></div>
         <div class="loader-section section-left"></div>
         <div class="loader-section section-right"></div>
      </div>
      <header>
         <div class="container">
            <div class="row">
               <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class="logo">
                     <img src="assets/images/logo_datamatics.png" alt="dfsf" /> 
                  </div>
               </div>
            </div>
         </div>
      </header>
      
      
      
    
      
      <%
      	    complianceNew ex = new complianceNew();
            List<compilanceValidatePojo> arrComp = new LinkedList<compilanceValidatePojo>();
            arrComp = ex.getResultList("1002");
      %> 
      <section class="axis_compilance_section">
         <div class="container">
            <div class="row">
               <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class=" martb10 ">
                     <!-- new row start -->
                     <div class="dash_compilnce">
                        <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                       Doc Check Rule</h3>
                </div>
                <div class="panel-body dash-sec-content">
                	<div class="row">
	      <ul>
	      <li>
	       <div class="item">
	            <span class="glyphicon glyphicon-ok"></span>
	            <div>Number Of Documents</div>
	           
    	        <p>
    	        12
    	        </p>
	        </div>
	        </li>
	        <li>
	        
	        <div class="item">
	            <span class="glyphicon glyphicon-ok"></span>
	            <div>Product Type</div>
	           
    	        <p>
    	         Export Letter Of Credit
    	        </p>
	        </div>
	        </li>
	         <li>
	       <div class="item">
	            <span class="glyphicon glyphicon-ok"></span>
	            <div>Name of Applicant</div>
	           
    	        <p>
    	        DINOWIC PTE LTD
    	        </p>
	        </div>
	        </li>
	        <li>
	        
	        <div class="item">
	            <span class="glyphicon glyphicon-ok"></span>
	            <div>Name of Beneficiary</div>
	          
    	        <p>
    	       ANHUI CO. LTD
    	        </p>
	        </div>
	        </li>
	         <li>
	       <div class="item">
	            <span class="glyphicon glyphicon-ok"></span>
	            <div>LC Reference No.</div>
	          
    	        <p>
    	      169834E2
    	        </p>
	        </div>
	        </li>
	        
	      </ul>
	       
	   

	   
	</div>
                
                
                
                
           <!--     <div class="dash-sec-content dash_sec_design clearfix">
                           <ul class="clearfix">
                              <li class="list-row">
                                 <a href="#">
                                 <span class="country-dashboard-icons adolescent-youth-dashboard-icon">22</span>
                                 <span class="country-dashboard-portal-title"> Number Of Document</span>
                                 </a>
                              </li>
                              <li class="list-row"> 
                                 <a href="#" class="hvr-ripple-out">
                                 <span class="country-dashboard-icons female-gential-mutilation-icon">Product type</span>
                                 <span class="country-dashboard-portal-title">Letter Of Credit</span>
                                 </a>
                              </li>
                           </ul>
                        </div>-->
                </div>           
            </div>
                     
                     
                     
                        
                      
                     </div>
                     <!-- new row end -->
                  </div>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class="board">
                     <div class="board-inner">
                        <ul class="nav nav-tabs custom_comliance_nav" id="myTab">
                           <div class="liner"></div>
                           <li class="active">
                              <a href="#Document_Checklist" data-toggle="tab" title="Document Checklist" class="blue-tooltip">
                              <span class="round-tabs one">
                              <i class="glyphicon glyphicon-file"></i>
                              </span> 
                              <p>Document Checklist</p>
                              </a>
                           </li>
                           <li><a href="#Document_Consistency_Checker" data-toggle="tab" title="Document Consistency Checker" class="blue-tooltip">
                              <span class="round-tabs two">
                              <i class="fa fa-file-text-o" aria-hidden="true"></i>
                              </span> 
                              <p>Document Consistency Checker</p>
                              </a>
                           </li>
                         <!--   <li><a href="#General_Compilance" data-toggle="tab" title="General Compilance" class="blue-tooltip">
                              <span class="round-tabs three">
                              <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                              </span> </a>
                           </li>
                           <li><a href="#Screening_Fade1" data-toggle="tab" title="Screening Fade" class="blue-tooltip">
                              <span class="round-tabs four">
                              <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                              </span> 
                              </a>
                           </li> -->
                           <li><a href="#document_status" data-toggle="tab" title="Status" class="blue-tooltip">
                              <span class="round-tabs five">
                              <i class="fa fa-file" aria-hidden="true"></i>
                              </span>
                              <p>Status</p></a>
                           </li>
                        </ul>
                     </div>
                     <div class="tab-content">
                        <div class="tab-pane fade in active" id="Document_Checklist">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                              <!--   <h3  onclick="openCity(event, 'UCP600')" class="text-center"><b>Document Checklist</b></h3> -->
                              <div class="tabcontent martb20">
                                 <div class="dash-header"><b>Document Checklist</b></div>
                                 <div class="table-responsive">
                                    <table id="Document_Checklist_table" class="display table table-bordered table-striped">
                                       <thead>
                                          <tr>
                                             <th>Document Type name</th>
                                             <th>Received</th>
                                             <th>Expected Original</th>
                                             <th>Received Original</th>                                             
                                             <th>Expected Copies</th>
                                             <th>Received Copies</th>
                                             <th>Details</th>
                                          </tr>
                                       </thead>
                                       <tbody>
                                       <tr>
                                             <td><b>Draft</b></td>
                                             <td> No</td>
                                             <td>1</td>
                                             <td>0</td>
                                             <td>1</td>
                                             <td>0</td>
                                             <td>
                                             1 Original Missing And
                                             1 Copy Missing 
                                             </td>
                                          </tr>
                                          <tr>
                                             <td><b>MT 700</b></td>
                                             <td> NA</td>
                                             <td>-</td>
                                             <td>-</td>
                                              <td>-</td>
                                             <td>-</td>
                                             <td></td>
                                          </tr>
                                          <tr>
                                             <td><b>Invoices</b></td>
                                             <td> Yes</td>
                                             <td>2</td>
                                             <td>1</td>
                                             <td>3</td>
                                             <td>2</td>
                                             <td> 1 Original Missing And
                                             1 Copy Missing </td>
                                          </tr>
                                           <tr>
                                             <td><b>Packing List</b></td>
                                             <td> Yes</td>
                                             <td>3</td>
                                             <td>2</td>
                                             <td>2</td>
                                             <td>1</td>
                                             <td>1 Original Missing And
                                             1 Copy Missing </td>
                                          </tr>
                                          <tr>
                                             <td><b>Bill of Lading</b></td>
                                             <td> Yes</td>
                                             <td>2</td>
                                             <td>1</td>
                                             <td>2</td>
                                             <td>1</td>
                                             <td>1 Original Missing And
                                             1 Copy Missing </td>
                                          </tr>
                                         
                                          <tr>
                                             <td><b>Insurance documents</b></td>
                                             <td>Yes</td>
                                             <td>2</td>
                                             <td>1</td>
                                             <td>2</td>
                                             <td>1</td>
                                             <td>1 Original Missing And
                                             1 Copy Missing </td>
                                          </tr>
                                          <tr>
                                             <td><b>Certificate Of Origin</b></td>
                                             <td> Yes</td>
                                             <td>3</td>
                                             <td>1</td>
                                             <td>2</td>
                                             <td>1</td>
                                             <td>2 Original Missing And
                                             1 Copy Missing </td>
                                          </tr>
                                          <tr>
                                             <td><b>Health certificate</b></td>
                                             <td> No</td>
                                             <td>0</td>
                                             <td>0</td>
                                             <td>0</td>
                                             <td>0</td>
                                             <td></td>
                                          </tr>
                                          <tr>
                                             <td><b>Halal Certificate</b></td>
                                             <td> No</td>
                                             <td>0</td>
                                             <td>0</td>
                                             <td>0</td>
                                             <td>0</td>
                                             <td></td>
                                          </tr>
                                         
                                       </tbody>
                                    </table>
                                 </div>
                                 <!-- TAB Content -->
                              </div>
                           </div>
                        </div>
                        <div class="tab-pane fade" id="Document_Consistency_Checker">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <!-- Modal -->
         <div id="output"></div>
   
     <!-- Modal -->
                               
                                                               <div id="UCP600" class="tabcontent martb20">
   <div  onclick="openCity(event, 'UCP600')" class="dash-header"><b>Document Consistency Checker</b>  </div>
                                 <div class="table-responsive">
                                    <table id="myTable" class="display table table-bordered table-striped">
                                       <thead>
                                          <tr>
                                             <th>SR.No.</th>
                                             <th>DESCRIPTION</th>
                                             <th>PASS</th>
                                             <th>FAIL</th>
                                             <th>COMMENT</th>
                                          </tr>
                                       </thead>
                                       <tbody>
                                          <%for(int i=0;i<arrComp.size();i++){ 
                                             compilanceValidatePojo cp = new compilanceValidatePojo();
                                             String strtrue="";
                                             String strfalse="";
                                             cp = arrComp.get(i);
                                             int srno=i+1;
                                             
                                             
 											int sizeCheck = arrComp.size();
 										//	System.out.println(sizeCheck+" sizeChecksizeChecksizeChecksizeCheck");

                                             
 											if(i > 24){
 	                                             %>
 	                                             
 	                                             <tr>
 	                                             <td><%=srno %></td>
 	                                             <td><%=cp.getCompRule()%></td>
 	                                             <td> - </td>
 	                                              <td> - </td>
 	                                             <td>Pending</td>
 	                                             </tr>
 	                                             
 	                                             <%}else{ %>
                                             
                                            
                                             
                                            
                                          <tr>
                                             <td><%=srno %></td>
                                             <td><%=cp.getCompRule()%></td>
                                             <%
                                                if(cp.getResult()){
                                                strtrue="fa fa-check-circle-o cust_pass_col";
                                                   } 
                                                else{
                                                strfalse="fa fa-times-circle-o cust_fail_col";
                                                
                                                }
                                                   %>
                                             <td>
                                                <i class="<%=strtrue %>" aria-hidden="true"  disabled="disabled"></i> 
                                                <!--    <input type="checkbox" name="true" disabled="disabled" <%=strtrue %> > -->
                                             </td>
                                             <td>
                                                <i class="<%=strfalse %>" aria-hidden="true"  disabled="disabled" ></i> 
                                                <!-- <input type="checkbox" name="false" disabled="disabled" <%=strfalse %> >
                                                   -->
                                             </td>
                                             <%if(strfalse.equals("fa fa-times-circle-o cust_fail_col")){
                                                 String passValue= cp.getCompRule()+"::::"+cp.getTransactionId()+"::::"+cp.getProcessID()+"::::false";
                                             %>
                                             <td> 
											<a href="#" id="<%=passValue%>" class="statusClass btn btn-primary">More Info </a>                                                
                                             </td>
                                             <%}else{
                                                 String passValue= cp.getCompRule()+"::::"+cp.getTransactionId()+"::::"+cp.getProcessID()+"::::true";

                                            	 %>
                                             <td> 
                                               <a href="#" id="<%=passValue%>" class="statusClass btn btn-primary">More Info </a>
                                             </td>
                                             <%} %>
                                          </tr>
                                          <%}}%> 
                                          
                                          
                                         
                                          
                                       </tbody>
                                    </table>
                                 </div>
                               
                                 <!-- TAB Content -->
                              </div>
                           </div>
                        </div>
                        <div class="tab-pane fade" id="General_Compilance">
                           <h3 class="head text-center">Lorem Ipsum...</h3>
                        </div>
                        <div class="tab-pane fade" id="Screening_Fade1">sss</div>
                        <div class="tab-pane fade" id="document_status">
                        	<div class="tabcontent martb20">
                                                            
                      
        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                <div class="pull-right">
                        <div class="ui-group-buttons cust_email_and_swift">
            <!--     <button type="button" class="btn btn-primary" href='mailto:?amita.singh@datamatics.com&amp;body=Hi,I found this and thought you might like it https://www.datamatics.com/'>Email</button> -->
                <a  class="btn btn-primary" data-toggle="modal" data-target="#emailModal">Email</a>
                <div class="or"></div>
                <button type="button" class="button btn btn-success" data-toggle="modal" data-target="#swiftModal">Swift</button>
            </div>
                    <!--     <a href='mailto:?amita.singh@datamatics.com&amp;body=Hi,I found this and thought you might like it https://www.datamatics.com/'><i class='fa fa-paper-plane'></i>Email</a> -->
                        </div>
          <div class="dash-header"><b>Document Status</b>  
          </div>
            <div class="panel-group" id="accordion">
              
                <div class="panel panel-default">
                    <div class="panel-heading clearfix">
    <%
   
   MoreInfoDAO md = new MoreInfoDAO();
   ArrayList<String> Pass_list=md.getStatusResult("1002", "true");
   int pass_count = Pass_list.size();
   ArrayList<String> Fail_list=md.getStatusResult("1002", "false");
   int fail_count = Fail_list.size();
   %>                       
       
                       
                          <div class="pull-left">
                           <h4 class="panel-title clearfix">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><i class="fa fa-check-circle-o pass_txt_col" aria-hidden="true"></i>Pass</a>
                             </h4>
                            </div>
                            <div class="pull-right"> Count:  &nbsp;<span class="green_col pad_3"><%=pass_count %></span></div>
                       
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                       <table class="table table-striped table-condensed table-bordered">
            						<thead>
            							<tr>
            								<th>Rule Description&nbsp;&nbsp;</th>
            							</tr>
            						</thead>   
            						<tbody>
            							<%for(int i=0;i<Pass_list.size();i++){ %>
            							<tr>
            								<td><%=Pass_list.get(i) %></td>							
            							</tr>
            							<%} %>
            						</tbody>
            					</table>
                        
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading clearfix">
                        <div class="pull-left">
                          <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"><i class="fail_txt_col fa fa-times-circle-o" aria-hidden="true"></i>Fail</a>
                        </h4>
                        </div>
                         <div class="pull-right"> Count: &nbsp; <span class="dan_col pad_3"><%=fail_count %></span></div>

                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                     <table class="table table-striped table-condensed">
            						<thead>
            							<tr>
            								<th>Rule Discription &nbsp;&nbsp;</th>
            							</tr>
            						</thead>   
            						<tbody>
            							<%for(int x=0;x<Fail_list.size();x++){ %>
            							<tr>
            								<td><%=Fail_list.get(x) %></td>							
            							</tr>
            							<%} %>	
            						</tbody>
            					</table>
                        </div>
                    </div>
                </div>
          
            </div>
        </div>
    </div>
                        
                        
                        
                        
                        
                        
                   
                   
                        
                        </div>
                        <div class="clearfix"></div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>
        <footer>
         <div class="container">
            <div class="row">
               <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                  <a href="https://www.datamatics.com/privacy-policy">Privacy Policy</a> &nbsp; | &nbsp; <a href="https://www.datamatics.com/terms-of-use">Terms of Use</a> &nbsp; | &nbsp; <a href="https://www.datamatics.com/sitemap">Sitemap</a>
               </div>
               <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                  <p>2019 Datamatics Global Services Limited. All Rights Reserved.</p>
               </div>
            </div>
         </div>
      </footer>
      
     <!-- Modal -->
<div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title pull-left" id="exampleModalLabel">Email</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <p>
     Dear Customer, We have received documents under your ref No- 169834E1, we have noted below discrepancy.

<br/>
<br/>
1) The effective date of insurance is later than the date of shipment; and / or<br/>
2) Currency should be consistent across the documents<br/>
3) The ports of Final Destination differ from those specified in the documentary credit<br/>
4) The ports of Discharge differ from those specified in the documentary credit<br/>
5) The documentary credit has not expired<br/>
6) A document may be dated prior to the issuance date of the credit, but must not be dated later than its date of presentation.<br/>
<br/>
<br/>
We request, you to please consult with beneficiary and get the same resolved, till then documents are lying at our counter with status "Hold"													
<br/>
<br/>
<br/>
Signature,<br/>
Stand Bank		
		
	</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
             </div>
    </div>
  </div>
</div>
<div class="modal fade" id="swiftModal" tabindex="-1" role="dialog" aria-labelledby="swiftModal" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title pull-left" id="exampleModalLabel">Swift</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      SWIFT Message<br/>
Sender : AMCONL2A<br/>
Message Type : 734<br/>
Receiver : OELBATSW<br/>
Message Text<br/>
20 : Senders TRN<br/>
EXP54321<br/>
21 : Presenting Banks Reference<br/>
IMP145322<br/>
32A : Date and Amount of Utilisation<br/>
130921GBP100000<br/>
73 : Charges Claimed<br/>
YOUR CHARGES GBP 100, CABLE GBP30, INTEREST GBP20,<br/>
33A : Total Amount Claimed<br/>
130927GBP100150<br/>
77J : Discrepancies<br/>
1) The effective date of insurance is later than the date of shipment; and / or<br/>
2) Currency should be consistent across the documents<br/>
3) The ports of Final Destination differ from those specified in the documentary credit<br/>
4) The ports of Discharge differ from those specified in the documentary credit<br/>
5) The documentary credit has not expired<br/>
6) A document may be dated prior to the issuance date of the credit, but must not be dated later than its date of presentation.<br/>

77B : Disposal of Documents<br/>
/HOLD/<br/>
End of Message Text/Trailer<br/>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      
      </div>
    </div>
  </div>
</div>
     </body>
</html>