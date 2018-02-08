function incrementProduct(id) {
    width = screen.width;
    if (width < 500) {
        var name = "number-for-add-" + id;
        var i = document.getElementById(name).value;
        if (i == 10) {
            document.getElementById(name).value = 0;
        } else {
            i = +i + 1;
            document.getElementById(name).value = i;
        }
    }
    return false;
}