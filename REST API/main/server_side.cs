[HttpPost]
public ActionResult Authenticate(String login, String password)
{
    try
    {
        User user = db.UsersList.Single(u => u.Login == login && u.Password ==
                                        password);
        user.Token = Guid.NewGuid().ToString();
        db.Save();
        return Json(new { response = "success", token = user.Token });
    }
    catch(Exception e)
    {
        return Json(new { response = "failed" });
    }
}



public ActionResult GetDebtors(String token)
{
    try
    {
        User user = db.UsersList.Single(u => u.Token == token);
        String debtorsJson = JsonConvert.SerializeObject(user.Debtors);
        return Json(new { response = "success", debtors = debtorsJson },
                    JsonRequestBehavior.AllowGet);
    }
    catch (Exception e)
    {
        return Json(new { response = "failed" },
                    JsonRequestBehavior.AllowGet);
    }
}



public ActionResult AddDebtor(string token, string dId, string dName, string
                              dPhone)
{
    try
    {
        User user = db.UsersList.Single(u => u.Token == token);
        Debtor debtor = new Debtor()
        {
            Name = dName, Id = dId, Phone = dPhone
        };
        user.Debtors.Add(debtor);
        db.Save();
        return Json(new { response = "success" }, JsonRequestBehavior.
                    AllowGet);
    }
    catch (Exception e)
    {
        return Json(new { response = "failed" }, JsonRequestBehavior.
                    AllowGet);
    }
}


[HttpPost]
public ActionResult DeleteDebtor(string token, string dId)
{
    try
    {
        User user = db.UsersList.Single(u => u.Token == token);
        Debtor debtor = user.Debtors.Single(d => d.Id == dId);
        user.Debtors.Remove(debtor);
        db.Save();
        return Json(new { response = "success" }, JsonRequestBehavior.
                    AllowGet);
    }
    catch (Exception e)
    {
        return Json(new { response = "failed" }, JsonRequestBehavior.
                    AllowGet);
    }
}
