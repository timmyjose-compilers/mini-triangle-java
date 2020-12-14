! Source: https://github.com/alyssa/MiniTriangleCompiler/blob/master/testFiles/scope.mt

let 

    var x: Integer
  var    y:     Integer
in
  begin
    ! this is x
    ! and x is 1
                              x := 1;
    y := 2;
    let
      var x: Integer; ! this is the inner x
    in
            begin ! this is the beginning of a block
        let
          var y: Integer;
        in
          getint(y); ! let's call this yin
        x := y; ! this is the inner x
      end

        putint(y); ! this should be 2
  



  putint(x); ! this should be 1
  

  end
