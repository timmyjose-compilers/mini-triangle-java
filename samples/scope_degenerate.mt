! Source: https://github.com/alyssa/MiniTriangleCompiler/blob/master/testFiles/scope.mt

let 
  var x: Integer;
  var y: Integer
in
  begin
    x := 11;
    y := 22;
    let
      var x: Integer
    in
      begin
        let
          var y: Integer
        in
          x := 100; ! let's call this yin
        x := y ! this is the inner x
      end;
    putint(y); ! this should be 22
    putint(x) ! this should be 11
  end
