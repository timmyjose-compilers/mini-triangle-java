let 
  proc P(i: Integer, b: Integer) ~
    let
      const d ~ chr(i // b + ord('0'))
    in
      if i < b then
        put(d)
      else
        begin
          P(i / b, b);
          put(d)
        end;

  var n: Integer
in
  begin
    getint(var n);
    P(n, 8)
  end