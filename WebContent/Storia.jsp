<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        
        <title>Chi siamo</title>
       
        <link rel="stylesheet" href="Storia.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- da modificare i font e colori credo in te e poi aggiungere immagini quando arrivano-->
    </head>
    <body>
        <section>
            <div class = "image">
               <img src="https://cdn.pixabay.com/photo/2017/08/26/23/37/business-2684758__340.png" id="foto">
            </div>
            <div class = "content">
                <h2>About Us</h2>
                <span><!-- line here --></span>
                <p id="prima">Una storia che ha origine prima della Seconda Guerra Mondiale una storia raccontata da ogni 
                    singola prelibatezza della pasticceria KindKaribe.
                    Nella nascente Caserta degli anni '30, i nonni Gentile avevano un importante e 
                    rinomato panificio in cui vigeva una regola fondamentale: l'utilizzo del lievito madre. 
                    Sessant'anni e due generazioni dopo, la stessa regola vive ancora nei lieviti della pasticceria KindKaribe, 
                    oggi capitanata da Marco con l'ausilio della moglie Ester e il figlio Arturo.</p>
                <p id="seconda" style="display:none;"> Ubicato in via Boh, 
                    la pasticceria KindKaribe offre ogni giorno una vastissima selezione di prodotti tipici napoletani, fatti con passione e 
                    sapienza. Delizie in cui perdersi tra la morbidezza di una pastiera, la croccantezza di una calda sfogliatella, 
                    fino ad inebriarsi del profumo del rum di un babà appena bagnato. Un'esplosione di piacere non solo partenopeo, 
                    la pasticceria KindKaribe prepara torte dal design innovativo pronte a regalare un'emozione sia visiva che gustativa.
                    La lavorazione del cacao, rigorosamente "monorigine", si trasforma in straordinari cioccolatini, praline e 
                    tavolette dal profumo ed aroma autentico. Cioccolato per golosi ma anche per esteti e intenditori.</p>
                <p id="terza" style="display:none;">  KindKarive non è solo pasticceria. Gustosi gelati, assolutamente artigianali, 
                    sono preparati con cura utilizzando solo ingredienti freschi e naturali. 
                    Una caffetteria di alta professionalità accompagna ogni piccolo peccato di gola e 
                    piacevoli momenti da concedersi da soli o in compagnia.</p>
                <ul class = "links">
                    <li id="work"><a href = "#">Storia</a></li>
                    <div class = "vertical-line"></div>
                    <li id="service"><a href = "#">Pasticceria</a></li>
                    <div class = "vertical-line"></div>
                    <li id="contact"><a href = "#">Gelateria</a></li>
                </ul>
            </div>
        </section><br><br>
       <script>
     $('#work').click(function(){
   $('#prima').show();
   $("#seconda").hide();
   $("#terza").hide();
   $("#foto").show();
});
$('#service').click(function(){
   $('#seconda').show();
   $("#prima").hide();
   $("#terza").hide();
   $("#foto").hide();
});    

$('#contact').click(function(){
   $('#terza').show();
   $("#prima").hide();
   $("#seconda").hide();
}); 
       </script>
       
    </body>
</html>
    