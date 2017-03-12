/**
 * Created by Pavel on 10.03.2017.
 */
function show(id, id1, id2, id3, id4, id5, id6) {
    var div = document.getElementById(id);
    var div1 = document.getElementById(id1);
    var div2 = document.getElementById(id2);
    var div3 = document.getElementById(id3);
    var div4 = document.getElementById(id4);
    var table = document.getElementById(id5);
    var elements = table.getElementsByTagName('input');
    var div6 = document.getElementById(id6);

    if (div.value == 'Закрыть') {
        div.value = 'Забронировать';
        div1.style.display = 'none';
        div2.style.display = 'none';
        div3.style.display = 'none';
        div4.style.display = 'none';
        for (var i = 0; i < elements.length; i++) {
            var input1 = elements[i];

            /*if (input1.getAttribute("id") == id ) {
                continue;
            }*/
                if(input1.getAttribute("class") == 'newclass')
                {
                    input1.style.display = 'none';
                }
            if(input1.getAttribute("class") == 'mynewclass')
            {
                input1.style.display = 'block';
            }
            else {
                input1.style.display = 'none';
            }
        }
    }
    else {
        div.value = 'Закрыть';
        /*div1.style.display = 'block';
         div2.style.display = 'block';*/
        div3.style.display = 'block';
        div4.style.display = 'block';
        for (var i = 0; i < elements.length; i++) {
            var input1 = elements[i];
            if (input1.getAttribute("id") == id) {
                continue;
            }

            if (input1.getAttribute("id") == id1 || input1.getAttribute("id") == id2 || input1.getAttribute("id") == id6) {
                input1.style.display = 'block';
            }

            else {
                input1.style.display = 'none';
            }
        }

    }

}


