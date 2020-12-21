let 
  var g: Integer;

  func F(m: Integer, n: Integer): Integer ~
    m * n;

  proc W(i: Integer) ~
    let 
      const s ~ i * i
    in
      begin
        putint(F(i, s));
        putint(F(s, s))
      end
in
  begin
    getint(var g);
    W(g + 1)
  end