<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body style="font-family: Arial, sans-serif; background-color: #f3f4f6; margin: 0; padding: 20px;">
<div style="max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 6px rgba(0,0,0,0.1);">

    <div style="background-color: #1e40af; padding: 30px; text-align: center;">
        <h1 style="color: #ffffff; margin: 0; font-size: 24px;">🥋 Convocation Arbitrage</h1>
    </div>

    <div style="padding: 30px;">
        <p style="font-size: 16px; color: #374151;">Bonjour <strong>${arbitreName}</strong>,</p>

        <p style="font-size: 16px; color: #374151;">${introMessage}</p>

        <div style="background-color: #f9fafb; border-left: 4px solid #1e40af; padding: 16px; border-radius: 8px; margin: 20px 0;">
            <p style="margin: 0 0 8px 0; font-size: 16px;"><strong>📋 Compétition :</strong> ${competitionName}</p>
            <p style="margin: 0; font-size: 16px;"><strong>📅 Date :</strong> ${date}</p>
            <p style="margin: 0; font-size: 16px;"><strong>📅 Heure :</strong> ${heure}</p>
            <p style="margin: 0; font-size: 16px;"><strong>📅 Ville :</strong> ${lieu}</p>
        </div>

        <#if message?has_content>
            <div style="background-color: #eff6ff; border-radius: 8px; padding: 16px; margin: 20px 0;">
                <div style="margin: 0; font-size: 15px; color: #1e40af;">${message?no_esc}</div>
            </div>
        </#if>

        <p style="font-size: 16px; color: #374151;">Merci de confirmer votre présence :</p>

        <div style="text-align: center; margin: 30px 0;">
            <a href="${acceptUrl}" style="display: inline-block; background-color: #16a34a; color: #ffffff; padding: 14px 32px; border-radius: 8px; text-decoration: none; font-weight: bold; font-size: 16px; margin-right: 16px;">✅ Accepter</a>
            <a href="${refuseUrl}" style="display: inline-block; background-color: #dc2626; color: #ffffff; padding: 14px 32px; border-radius: 8px; text-decoration: none; font-weight: bold; font-size: 16px;">❌ Refuser</a>
        </div>

        <p style="font-size: 13px; color: #9ca3af; text-align: center;">Ce lien est à usage unique.</p>
    </div>

    <div style="background-color: #f3f4f6; padding: 20px; text-align: center;">
        <p style="font-size: 13px; color: #6b7280; margin: 0;">Système de gestion des arbitres — Ne pas répondre à cet email</p>
    </div>

</div>
</body>
</html>